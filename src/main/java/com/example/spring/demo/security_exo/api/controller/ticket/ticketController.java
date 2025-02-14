package com.example.spring.demo.security_exo.api.controller.ticket;

import com.example.spring.demo.security_exo.api.model.ticket.CreateTicketRequestBody;
import com.example.spring.demo.security_exo.api.model.ticket.StatusUpdateRequest;
import com.example.spring.demo.security_exo.api.model.ticket.TicketResponseBody;
import com.example.spring.demo.security_exo.bll.service.TicketService;
import com.example.spring.demo.security_exo.bll.service.UserService;
import com.example.spring.demo.security_exo.dal.domain.Ticket;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
public class ticketController {
    private final TicketService ticketService;
    private final UserService userService;

    public ticketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_TECHNICIEN')")
    @PostMapping("/create")
    public ResponseEntity<TicketResponseBody> createTicket(@RequestBody CreateTicketRequestBody requestBody, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        Ticket ticket = ticketService.createTicket(CreateTicketRequestBody.toEntity(requestBody, user));

        return ResponseEntity.ok(TicketResponseBody.fromEntity(ticket));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_TECHNICIEN')")
    @GetMapping("personaltickets")
    public ResponseEntity<List<TicketResponseBody>> getPersonalTickets(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        Set<Ticket> tickets = ticketService.getTicketForUser(user.getId());
        List<TicketResponseBody> ticketResponseBodies = tickets.stream()
                .map(TicketResponseBody::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ticketResponseBodies);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TECHNICIEN')")
    @PutMapping("/{ticketId}/assign")
    public ResponseEntity<TicketResponseBody> assignTicket(@PathVariable Long ticketId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        Ticket ticket = ticketService.updateAssignedTo(ticketId, user);

        return ResponseEntity.ok(TicketResponseBody.fromEntity(ticket));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TECHNICIEN')")
    @PutMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponseBody> changeStatus(@PathVariable Long ticketId, @RequestBody StatusUpdateRequest request) {
        Ticket ticket = ticketService.updateStatus(ticketId, request.getStatus());
        return ResponseEntity.ok(TicketResponseBody.fromEntity(ticket));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.delete(ticketId);
        return ResponseEntity.ok().build();
    }
}
