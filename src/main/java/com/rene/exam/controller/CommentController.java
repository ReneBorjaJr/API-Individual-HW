package com.rene.exam.controller;

import com.rene.exam.dto.comment.CommentRequest;
import com.rene.exam.service.CommentService;
import com.rene.exam.successfulresponse.DeleteSuccessful;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/comment")
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequest comment) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @GetMapping(value = "/comment/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/comments")
    public ResponseEntity<?> getAllComments() {
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping(value = "/comments/{postId}")
    public ResponseEntity<?> getAllCommentsByPost(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getAllCommentsByPost(postId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/comment/{commentId}")
    public ResponseEntity<DeleteSuccessful> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @PutMapping(value = "/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(commentId, commentRequest), HttpStatus.OK);
    }
}
