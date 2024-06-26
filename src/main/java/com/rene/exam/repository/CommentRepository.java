package com.rene.exam.repository;

import com.rene.exam.domain.Comment;
import com.rene.exam.dto.comment.CommentResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query(value = "SELECT * FROM COMMENT WHERE COMMENT_ID = ?1", nativeQuery = true)
    Comment findCommentById(Long id);

    @Query(value = "SELECT * FROM COMMENT WHERE POST_ID = ?1", nativeQuery = true)
    List<Comment> findAllCommentsByPost(Long postId);

    @Query(value = "SELECT * FROM COMMENT", nativeQuery = true)
    List<Comment> findAllComments();
}
