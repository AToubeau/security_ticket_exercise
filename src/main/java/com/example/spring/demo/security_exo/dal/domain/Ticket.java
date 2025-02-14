package com.example.spring.demo.security_exo.dal.domain;

import com.example.spring.demo.security_exo.dal.domain.base.BaseEntity;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket extends BaseEntity<Long> {
    private String title;
    private String description;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_creator_id")
    private User userCreator;
    @ManyToOne
    @JoinColumn(name = "user_assigned_to_id")
    private User userAssignedTo;

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public User getUserAssignedTo() {
        return userAssignedTo;
    }

    public void setUserAssignedTo(User assigned_to) {
        this.userAssignedTo = assigned_to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
