package com.edriving.restboot.health;

public class HealthResponse {
    private HealthStatus status;
    private String details;

    public HealthResponse(HealthStatus status) {
        this(status, null);
    }

    public HealthResponse(HealthStatus status, String details) {
        this.status = status;
        this.details = details;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
