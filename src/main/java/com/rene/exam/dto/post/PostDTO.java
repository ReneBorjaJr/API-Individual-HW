package com.rene.exam.dto.post;

import com.rene.exam.domain.Post;

public class PostDTO {

    private Long postId;
    private String title;
    private String content;

    public PostDTO(Post post) {
        this.content = post.getContent();
        this.postId = post.getId();
        this.title = post.getTitle();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
