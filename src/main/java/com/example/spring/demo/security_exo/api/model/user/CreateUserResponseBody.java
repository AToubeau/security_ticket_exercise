package com.example.spring.demo.security_exo.api.model.user;

import com.example.spring.demo.security_exo.dal.domain.User;

public class CreateUserResponseBody {
    private Long id;
    private String username;
    private String email;
    private String token;

    public CreateUserResponseBody( Long id, String username, String email) {
        this.username = username;
        this.id = id;
        this.email = email;
    }

    public static CreateUserResponseBody fromEntity(User user) {
        return new CreateUserResponseBody(user.getId(), user.getUsername(), user.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
