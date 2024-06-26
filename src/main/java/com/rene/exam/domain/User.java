package com.rene.exam.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rene.exam.domain.enums.Gender;
import com.rene.exam.enumdeserializer.GenderDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "USERNAME")
    private String username;

    @NotNull(message = "Gender cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

    public User(Gender gender, Long id, String username) {
        this.gender = gender;
        this.id = id;
        this.username = username;
    }

    public User() {
    }

    public @NotNull Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }
}
