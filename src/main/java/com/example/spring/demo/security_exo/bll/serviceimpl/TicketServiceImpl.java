package com.example.spring.demo.security_exo.bll.serviceimpl;

import com.example.spring.demo.security_exo.bll.service.TicketService;
import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;
import com.example.spring.demo.security_exo.dal.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Set<Ticket> getTicketForUser(Long userId) {
        return ticketRepository.findForUser(userId);
    }

    @Override
    public Ticket updateAssignedTo(Long ticketId, User user) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setUserAssignedTo(user);

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateStatus(Long ticketId, Status status) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
