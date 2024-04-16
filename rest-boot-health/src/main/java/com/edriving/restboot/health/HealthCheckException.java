package com.edriving.restboot.health;

public class HealthCheckException extends Exception {

    public HealthCheckException(String message) {
        super(message);
    }

    public HealthCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public HealthCheckException(Throwable cause) {
        super(cause);
    }

    public HealthCheckException() {
        super();
    }
}
