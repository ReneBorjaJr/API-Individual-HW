package com.rene.exam.controller;

import com.rene.exam.domain.Post;

import com.rene.exam.dto.post.PostRequest;
import com.rene.exam.dto.post.PostResponse;
import com.rene.exam.service.PostService;
import com.rene.exam.successfulresponse.ApiResponse;
import com.rene.exam.successfulresponse.DeleteSuccessful;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/post")
    public ResponseEntity<?> createPost(@RequestBody @Valid PostRequest postRequest) {
        return new ResponseEntity<>(postService.createPost(postRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/post")
    public ResponseEntity<?> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping(value = "/post/user/{userId}")
    public ResponseEntity<?> getPostByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getPostByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/post/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody @Valid PostRequest postRequest) {
        return new ResponseEntity<>(postService.updatePost(postId, postRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }
}
