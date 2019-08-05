let token = document.querySelector('meta[name="_csrf"]').getAttribute('content');

const home = Vue.component('home', {
    template: '#home-template'
});

const pageNotFound = Vue.component('page-not-found', {
    template: '#page-not-found-template'
});

Vue.component('navbar', {
    template: '#navbar-template'
});

const about = Vue.component('about', {
    template: '#about-template'
});

Vue.component('post', {
    props: ['post'],
    computed: {
        formattedTimestamp: function() {
            return new String(this.post.timestamp).split('T')[0]
        },
        parsedBody: function() {
            if(!this.post.id) return '<h1 class="text-center mt-1">404 - Post not found</h1>'; //ID cannot be undefined
            return this.post.body ? marked(this.post.body, { sanitize: true }) : '';
        }
    },
    template: '#post-template'
})

Vue.component('page-controls', {
    data: function() {
        return {
            currPage: 0,
            pagesCount: 0
        }
    },
    mounted: function() {
        this.currPage = parseInt(this.$route.params.num);
        fetch('/api/pageCount')
            .then(res => res.text())
            .then(data => this.pagesCount = data)
            .catch(err => console.error(err));
    },
    watch: {
        $route() {
            this.currPage = parseInt(this.$route.params.num);
        }
    },
    template: '#page-controls-template'
})

const postDetails = Vue.component('post-details', {
    props: ['id'],
    data: function() {
        return {
            post: null
        }
    },
    mounted: function() {
        fetch('/api/read/' + this.id)
            .then(res => res.json())
            .then(json => this.post = json)
            .catch(err => console.error(err));
    },
    template: '#post-details-template'
})

Vue.component('post-controls', {
    props: ['id', 'showReadMore'],
    methods: {
        remove: function() {
            if (confirm("Are you sure you want to remove this post?")) {
                let formData = new FormData();
                formData.append('id', this.id);
                fetch('/api/delete', { method: 'POST', headers: { 'X-CSRF-TOKEN': token }, body: formData })
                    .then(res => this.$el.parentNode.remove())
                    .catch(error => alert('An error occurred while deleting post'));

            }
        }
    },
    template: '#post-controls-template'
})

const postForm = Vue.component('post-form', {
    props: ['id', 'msg'],
    data: function() {
        return {
            post: {}
        }
    },
    watch: {
        $route() {
            this.load();
        }
    },
    mounted: function() {
        this.load();
    },
    computed: {
        parsedMarkdown: function() {
            return this.post.body ? marked(this.post.body, { sanitize: true }) : '';
        }
    },
    methods: {
        load: function() {
            //ID=0 -> create new post, else fetch existing post
            this.id ? fetch('/api/read/' + this.id).then(res => res.json()).then(json => this.post = json).catch(err => console.error(err)) : this.post = { id: 0, title: '', body: '' };
        },
        submit: function() {
            fetch('/api/save', { method: 'POST', headers: { 'X-CSRF-TOKEN': token }, body: new FormData(document.querySelector('#post-form')) })
                .then(res => router.push({ name: 'page', params: { num: 1 }}))
                .catch(error => alert('Error while saving'));

        }
    },
    template: '#post-form-template'
})

const login = Vue.component('login-form', {
    template: '#login-form-template'
})

Vue.component('return', {
    methods: {
        goBack: function() {
            window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/');
        }
    },
    template: '#return-template'
})

const page = Vue.component('page', {
    props: ['pageNum'],
    data: function() {
        return {
            posts: []
        }
    },
    mounted: function() {
        this.load();
    },
    watch: {
        $route() {
            this.load();
        }
    },
    methods: {
        load: function() {
            fetch('/api/page/' + this.pageNum)
                .then(res => res.json())
                .then(json => this.posts = json)
                .catch(err => console.error(err));
        }
    },
    template: '#page-template'
})
