package com.rene.exam.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rene.exam.domain.enums.Gender;
import com.rene.exam.enumdeserializer.GenderDeserializer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Gender cannot be empty")
    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @NotNull(message = "Gender cannot be empty") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Gender cannot be empty") Gender gender) {
        this.gender = gender;
    }
}
