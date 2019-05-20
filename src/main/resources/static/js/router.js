Vue.use(VueRouter);

const routes = [
    { path: '/', component: home },
    { path: '/about', component: about },
    { path: '/login', component: login },
    { path: '/create', name: 'create', component: postForm, props: { id: 0, msg: 'Create post' } },
    { path: '/page/:num', name: 'page', component: page, props: (route) => ({ pageNum: route.params.num }) },
    { path: '/read/:id', name: 'read', component: postDetails, props: (route) => ({ id: route.params.id }) },
    { path: '/edit/:id', name: 'edit', component: postForm, props: (route) => ({ id: route.params.id, msg: 'Edit post' }) }
];

const router = new VueRouter({
    routes,
    mode: 'history'
});
