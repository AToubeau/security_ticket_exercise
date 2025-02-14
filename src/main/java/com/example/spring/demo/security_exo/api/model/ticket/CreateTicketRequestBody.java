package com.example.spring.demo.security_exo.api.model.ticket;

import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;

public class CreateTicketRequestBody {
    private String title;
    private String description;

    public CreateTicketRequestBody(String title, String description, Status status) {
        this.title = title;
        this.description = description;
    }

    public static Ticket toEntity(CreateTicketRequestBody createTicketRequestBody, User user) {
        Ticket ticket = new Ticket();
        ticket.setTitle(createTicketRequestBody.getTitle());
        ticket.setDescription(createTicketRequestBody.getDescription());
        ticket.setStatus(Status.OPEN);
        ticket.setUserCreator(user);

        return ticket;

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
}
