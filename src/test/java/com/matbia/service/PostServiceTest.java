package com.matbia.service;

import com.matbia.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    public void saveOrUpdate() {
        Post post = new Post();
        post.setTitle("Test title");
        post.setBody("Test body");

        postService.saveOrUpdate(post);
        assertTrue(post.getId() > 0);

        assertFalse(postService.saveOrUpdate(new Post()));
    }

    @Test
    public void delete() {
        Post post = new Post();
        post.setTitle("Mock Title");
        post.setBody("Mock Body");

        postService.saveOrUpdate(post);

        assertTrue(postService.delete(post.getId()));
    }
}