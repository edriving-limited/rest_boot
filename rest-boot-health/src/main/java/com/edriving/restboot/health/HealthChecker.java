package com.edriving.restboot.health;

public interface HealthChecker {

    void check() throws HealthCheckException;
}
