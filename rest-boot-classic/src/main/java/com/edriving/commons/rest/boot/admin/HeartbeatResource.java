package com.edriving.commons.rest.boot.admin;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class HeartbeatResource {
    private static final Heartbeat IMPL = new HeartbeatImplSimple();
    @GET
    @Path("/heartbeat")
    @Produces(MediaType.TEXT_PLAIN)
    public String heartbeat() {
        return IMPL.probe();
    }
}
