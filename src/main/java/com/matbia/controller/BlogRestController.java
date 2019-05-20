package com.matbia.controller;

import com.matbia.model.Post;
import com.matbia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BlogRestController {
    @Autowired
    private PostService postService;

    @GetMapping("pageCount")
    public long pageCount() {
        return postService.getPageCount();
    }

    @GetMapping("page/{id}")
    public List<Post> loadPage(@PathVariable("id") int pageId) {
        return postService.getPage(pageId);
    }

    @GetMapping("read/{id}")
    public Post loadPost(@PathVariable("id") long id) {
        return postService.getOne(id).orElse(new Post());
    }

    @PostMapping("save")
    public ResponseEntity setPost(@ModelAttribute("post") Post post) {
        return postService.saveOrUpdate(post) ?
                ResponseEntity.status(HttpStatus.OK).body(post) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

    @PostMapping("delete")
    public ResponseEntity deletePost(@RequestParam("id") long id) {
        return postService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).body(id) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }
}
