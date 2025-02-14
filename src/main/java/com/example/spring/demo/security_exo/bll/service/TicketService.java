package com.example.spring.demo.security_exo.bll.service;

import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Set<Ticket> getTicketForUser(Long id);
    Ticket updateAssignedTo(Long ticketId, User user);
    Ticket updateStatus(Long ticketId, Status status);
    void delete(Long ticketId);
}
