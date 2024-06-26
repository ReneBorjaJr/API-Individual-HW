package com.rene.exam.dto.comment;

import com.rene.exam.domain.Comment;
import com.rene.exam.domain.Post;
import com.rene.exam.dto.post.PostResponse;
import com.rene.exam.dto.user.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class CommentResponse {

    private Long userId;
    private String gender;
    private String username;
    private Long postId;
    private String title;
    private String postContent;
    private List<CommentDTO> comments;


    public CommentResponse(Post post, List<Comment> comments) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.postContent = post.getContent();
        this.userId = post.getUser().getId();
        this.gender = post.getUser().getGender().toString();
        this.username = post.getUser().getUsername();

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOS.add(new CommentDTO(comment));
        }
        this.comments = commentDTOS;
    }


    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

