package com.edriving.commons.rest.boot.admin;


import jakarta.ws.rs.ext.Provider;

@Provider
public interface Heartbeat {
    String probe();
}
