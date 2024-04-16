package com.edriving.commons.rest.boot.admin;

public class HeartbeatImplSimple implements Heartbeat {

    @Override
    public String probe() {
        return "I am alive";
    }
}
