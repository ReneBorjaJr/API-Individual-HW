package com.rene.exam.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rene.exam.domain.User;

import java.util.Objects;

public class UserResponse {

    private Long id;

    private String username;

    private String gender;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.gender = user.getGender().toString();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
