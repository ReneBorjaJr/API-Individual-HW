package com.rene.exam.service;

import com.rene.exam.domain.Comment;
import com.rene.exam.domain.Post;
import com.rene.exam.dto.comment.CommentRequest;
import com.rene.exam.dto.comment.CommentResponse;
import com.rene.exam.exceptions.ResourceNotFoundException;
import com.rene.exam.repository.CommentRepository;
import com.rene.exam.repository.PostRepository;
import com.rene.exam.successfulresponse.DeleteSuccessful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentResponse createComment(CommentRequest commentRequest) {
        Post post = checkIfPostExists(commentRequest.getPostId());

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setPostId(commentRequest.getPostId());
        comment.setPost(post);
        commentRepository.save(comment);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        logger.info("Comment created successfully");
        return new CommentResponse(post, comments);
    }

    public CommentResponse getCommentById(Long id) {
        Comment comment = checkIfCommentExists(id);
        Post post = comment.getPost();
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        logger.info("Comment with id: " + id + " retrieved successfully");
        return new CommentResponse(post, comments);
    }


    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAllComments();
        Map<Post, List<Comment>> commentsByUser = new HashMap<>();

        for (Comment comment : comments) {
            Post post = comment.getPost();
            if (!commentsByUser.containsKey(post)) {
                commentsByUser.put(post, new ArrayList<>());
            }
            commentsByUser.get(post).add(comment);
        }
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Map.Entry<Post, List<Comment>> entry : commentsByUser.entrySet()) {
            commentResponses.add(new CommentResponse(entry.getKey(), entry.getValue()));
        }
        logger.info("All comments retrieved successfully");
        return commentResponses;
    }


    public CommentResponse getAllCommentsByPost(Long postId) {
        Post post = checkIfPostExists(postId);
        List<Comment> comments = commentRepository.findAllCommentsByPost(postId);
        if (comments.isEmpty()) {
            throw new ResourceNotFoundException("No comments found for post with id: " + postId);
        }
        logger.info("Comments for post with id: " + postId + " retrieved successfully");
        return new CommentResponse(post, comments);
    }

    public DeleteSuccessful deleteComment(Long commentId) {
        Comment comment = checkIfCommentExists(commentId);

        commentRepository.delete(comment);
        logger.info("Comment with id: " + commentId + " deleted successfully");
        return new DeleteSuccessful(HttpStatus.OK.value(), "Comment with id: " + commentId + " successfully deleted");
    }

    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        Comment oldComment = checkIfCommentExists(commentId);
        Post post = checkIfPostExists(commentRequest.getPostId());

        if (!oldComment.getPostId().equals(commentRequest.getPostId())) {
            throw new ResourceNotFoundException("Comment with id: " + commentId + " does not belong to post with id: " + post.getId());
        }

        oldComment.setContent(commentRequest.getContent());
        oldComment.setPostId(commentRequest.getPostId());
        oldComment.setPost(post);

        commentRepository.save(oldComment);
        List<Comment> comments = new ArrayList<>();
        comments.add(oldComment);

        logger.info("Comment with id: " + commentId + " updated successfully");

        return new CommentResponse(post, comments);
    }


    private Post checkIfPostExists(Long postId) {
        Post post = postRepository.findPostById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("Post with id: " + postId + " does not exist");
        }
        return post;
    }

    private Comment checkIfCommentExists(Long commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment with id: " + commentId + " does not exist");
        }
        return comment;
    }
}