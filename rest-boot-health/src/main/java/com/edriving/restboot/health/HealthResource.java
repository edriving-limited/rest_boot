package com.edriving.restboot.health;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health")
public class HealthResource {

    private final Provider<HealthChecker> healthCheckerProvider;

    @Inject
    public HealthResource(Provider<HealthChecker> healthCheckerProvider) {
        this.healthCheckerProvider = healthCheckerProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthResponse health() {
        if (healthCheckerProvider.get() != null) {
            try {
                healthCheckerProvider.get().check();
            } catch (HealthCheckException e) {
                return new HealthResponse(HealthStatus.DOWN, e.getMessage());
            }
        }

        return new HealthResponse(HealthStatus.UP);
    }
}
