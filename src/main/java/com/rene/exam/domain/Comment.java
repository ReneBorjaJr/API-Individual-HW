package com.rene.exam.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @NotEmpty
    @Column(name = "CONTENT")
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
    private Post post;

    @Column(name = "POST_ID")
    private Long postId;

    public Comment(String content, Long id, Long postId) {
        this.content = content;
        this.id = id;
        this.postId = postId;
    }

    public Comment() {
    }

    public @NotEmpty String getContent() {
        return content;
    }

    public void setContent(@NotEmpty String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Post getPost() {
        return post;
    }

    public void setPost(@NotNull Post post) {
        this.post = post;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
