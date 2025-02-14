package com.example.spring.demo.security_exo.api.model.user;

import com.example.spring.demo.security_exo.dal.domain.Role;
import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;

import java.util.Set;

public class UserDetailResponseBody {
    private Long id;
    private String username;
    private String email;
    private String token;
    private Set<Role> roles;
    private Set<Ticket> ticketsCreated;
    private Set<Ticket> ticketsAssigned;

    public UserDetailResponseBody(
            Long id,
            String username,
            String email,
            Set<Role> roles,
            Set<Ticket> ticketsCreated,
            Set<Ticket> ticketsAssigned
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.ticketsCreated = ticketsCreated;
        this.ticketsAssigned = ticketsAssigned;
    }

    public static UserDetailResponseBody fromEntity(User user) {
        return new UserDetailResponseBody(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getTicketsCreated(),
                user.getTicketsAssigned()
                );
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

    public String getToken() {
        return token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Ticket> getTicketsCreated() {
        return ticketsCreated;
    }

    public void setTicketsCreated(Set<Ticket> ticketsCreated) {
        this.ticketsCreated = ticketsCreated;
    }

    public Set<Ticket> getTicketsAssigned() {
        return ticketsAssigned;
    }

    public void setTicketsAssigned(Set<Ticket> ticketsAssigned) {
        this.ticketsAssigned = ticketsAssigned;
    }
}
