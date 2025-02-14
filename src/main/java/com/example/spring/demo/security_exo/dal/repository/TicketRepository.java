package com.example.spring.demo.security_exo.dal.repository;

import com.example.spring.demo.security_exo.dal.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.userCreator.id = :userId")
    Set<Ticket> findForUser(Long userId);
}
