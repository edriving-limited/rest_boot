package com.edriving.restboot.jersey.exception;

public class ExceptionMessage {
    private final int httpStatus;
    private final String message;

    public ExceptionMessage(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }


}
