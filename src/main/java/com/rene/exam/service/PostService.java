package com.rene.exam.service;

import com.rene.exam.domain.Post;
import com.rene.exam.domain.User;

import com.rene.exam.dto.post.PostDTO;
import com.rene.exam.dto.post.PostRequest;
import com.rene.exam.dto.post.PostResponse;

import com.rene.exam.dto.user.UserResponse;
import com.rene.exam.exceptions.ResourceNotFoundException;
import com.rene.exam.repository.PostRepository;
import com.rene.exam.repository.UserRepository;
import com.rene.exam.successfulresponse.ApiResponse;
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
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostResponse createPost(PostRequest postRequest) {
        // find if the user exists
        User user = checkIfUserExists(postRequest.getUserId());
        // create the post
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUserId(postRequest.getUserId());
        post.setUser(user);
        postRepository.save(post);
        // save the post
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        logger.info("Post created successfully");
        // return the post
        return new PostResponse(user, posts);

    }

    public PostResponse getPostById(Long id) {
        Post post = checkIfPostExists(id);
        User user = post.getUser();
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        logger.info("Post with id: {} fetched successfully", id);
        return new PostResponse(user, posts);
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAllPosts();
        Map<User, List<Post>> postsByUser = new HashMap<>();

        for (Post post : posts) {
            User user = post.getUser();
            if (!postsByUser.containsKey(user)) {
                postsByUser.put(user, new ArrayList<>());
            }
            postsByUser.get(user).add(post);
        }

        List<PostResponse> postResponses = new ArrayList<>();
        for (Map.Entry<User, List<Post>> entry : postsByUser.entrySet()) {
            postResponses.add(new PostResponse(entry.getKey(), entry.getValue()));
        }
        logger.info("All posts fetched successfully");
        return postResponses;
    }


    public PostResponse getPostByUserId(Long userId) {
        User user = checkIfUserExists(userId);
        List<Post> posts = postRepository.findAllPostByUserId(userId);
        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("No posts found");
        }
        logger.info("Posts with user id: {} fetched successfully", userId);
        return new PostResponse(user, posts);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        // find if the post exists
        Post oldPost = checkIfPostExists(postId);
        User user = checkIfUserExists(postRequest.getUserId());

        //check if post belongs to user
        if (!oldPost.getUserId().equals(postRequest.getUserId())) {
            throw new ResourceNotFoundException("Post with id: " + postId + " does not belong to user with id: " + user.getId());
        }

        //update the post's fields
        oldPost.setTitle(postRequest.getTitle());
        oldPost.setContent(postRequest.getContent());
        oldPost.setUser(user);

        //save the updated post
        postRepository.save(oldPost);
        List<Post> posts = new ArrayList<>();
        posts.add(oldPost);
        // save the updated post
        logger.info("Post with id: {} updated successfully", postId);
        return new PostResponse(user, posts);
    }

    public DeleteSuccessful deletePost(Long postId) {
        // check if post exists
        Post post = checkIfPostExists(postId);
        // if it does, delete it
        postRepository.delete(post);
        // return success
        logger.info("Post with id: {} deleted successfully", postId);
        return new DeleteSuccessful(HttpStatus.OK.value(), "Post with id: " + postId + " successfully deleted");
    }

    private User checkIfUserExists(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User with id: " + userId + " does not exist");
        }
        return user;
    }

    private Post checkIfPostExists(Long postId) {
        Post post = postRepository.findPostById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("Post with id: " + postId + " does not exist");
        }
        return post;
    }
}
