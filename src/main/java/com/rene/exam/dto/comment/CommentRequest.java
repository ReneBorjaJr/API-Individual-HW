package com.rene.exam.dto.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CommentRequest {

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Post id cannot be null")
    private Long postId;

    public @NotEmpty String getContent() {
        return content;
    }

    public void setContent(@NotEmpty String content) {
        this.content = content;
    }

    public @NotNull Long getPostId() {
        return postId;
    }

    public void setPostId(@NotNull Long postId) {
        this.postId = postId;
    }
}
