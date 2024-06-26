package com.rene.exam.dto.post;

import com.rene.exam.domain.Post;
import com.rene.exam.domain.User;
import com.rene.exam.dto.user.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {


    private Long userId;
    private String gender;
    private String username;
    private List<PostDTO> posts = new ArrayList<>();


    public PostResponse(User user, List<Post> posts) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.gender = user.getGender().toString();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts) {
            postDTOS.add(new PostDTO(post));
        }
        this.posts = postDTOS;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
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
