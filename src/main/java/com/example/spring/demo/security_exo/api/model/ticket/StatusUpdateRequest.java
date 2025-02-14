package com.example.spring.demo.security_exo.api.model.ticket;

import com.example.spring.demo.security_exo.dal.domain.enums.Status;

public class StatusUpdateRequest {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
