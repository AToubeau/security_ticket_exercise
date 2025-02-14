package com.example.spring.demo.security_exo.bll.service;

import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Service
public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Set<Ticket> getTicketForUser(Long id);
    Ticket updateAssignedTo(Long ticketId, User user);
    Ticket updateStatus(Long ticketId, Status status);
    void delete(Long ticketId);
}
