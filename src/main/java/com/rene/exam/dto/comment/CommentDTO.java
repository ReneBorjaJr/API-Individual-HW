package com.rene.exam.dto.comment;

import com.rene.exam.domain.Comment;

public class CommentDTO {

    private Long id;
    private String content;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
