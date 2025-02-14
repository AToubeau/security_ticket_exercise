package com.example.spring.demo.security_exo.api.model.ticket;

import com.example.spring.demo.security_exo.api.model.user.CreateUserResponseBody;
import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;

public class TicketResponseBody {
    private String title;
    private String description;
    private Status status;
    private CreateUserResponseBody userCreator;
    private CreateUserResponseBody userResponsable;

    public TicketResponseBody(String title, String description, Status status, User userCreator, User userResponsable) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userCreator = new CreateUserResponseBody(userCreator.getId(), userCreator.getUsername(), userCreator.getEmail());
        this.userResponsable = userResponsable == null ? null : new CreateUserResponseBody(userResponsable.getId(), userResponsable.getUsername(), userResponsable.getEmail());
    }

    public static TicketResponseBody fromEntity(Ticket ticket) {
        return new TicketResponseBody(
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getUserCreator(),
                ticket.getUserAssignedTo()
                );
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

    public CreateUserResponseBody getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(CreateUserResponseBody userCreator) {
        this.userCreator = userCreator;
    }

    public CreateUserResponseBody getUserResponsable() {
        return userResponsable;
    }

    public void setUserResponsable(CreateUserResponseBody userResponsable) {
        this.userResponsable = userResponsable;
    }
}
