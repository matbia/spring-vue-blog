package com.matbia.service;

import com.matbia.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private PostService postService;

    private Post post;

    @Before
    public void init() {
        post = new Post();
        post.setTitle("Test title");
        post.setBody("Test body");
        postService.saveOrUpdate(post);
    }

    @Test
    @Order(1)
    public void saveOrUpdate() {
        assertTrue(post.getId() > 0);
        assertFalse(postService.saveOrUpdate(new Post()));
    }

    @Test
    @Order(2)
    public void getOne() {
        Optional<Post> found = postService.getOne(this.post.getId());
        assertTrue(found.isPresent());
        assertEquals(found.get().getTitle(), this.post.getTitle());
        assertEquals(found.get().getBody(), this.post.getBody());
    }

    @Test
    @Order(3)
    public void getPage() {
        List<Post> page = postService.getPage(1);
        assertFalse(page.isEmpty());
        assertTrue(page.size() <= 5);
    }

    @Test
    @Order(4)
    public void getPageCount() {
        assertTrue(postService.getPageCount() > 0);
    }

    @Test
    @Order(5)
    public void delete() {
        assertTrue(postService.delete(this.post.getId()));
    }
}