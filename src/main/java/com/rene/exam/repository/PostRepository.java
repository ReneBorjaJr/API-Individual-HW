package com.rene.exam.repository;

import com.rene.exam.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "SELECT * FROM POST WHERE POST_ID = ?1", nativeQuery = true)
    Post findPostById(Long postId);

    @Query(value = "SELECT * FROM POST WHERE USER_ID = ?1", nativeQuery = true)
    List<Post> findAllPostByUserId(Long userId);

    @Query(value = "SELECT * FROM POST", nativeQuery = true)
    List<Post> findAllPosts();

//    @Query(value = "SELECT USER_ID FROM POST WHERE USER_ID = ?1", nativeQuery = true)
//    int findUserId(Long userId);
}
