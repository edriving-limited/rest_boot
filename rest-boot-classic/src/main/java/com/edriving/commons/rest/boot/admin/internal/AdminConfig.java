package com.edriving.commons.rest.boot.admin.internal;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties"
})
public interface AdminConfig extends Config {
    String ADMIN_PORT_PROPERTY = "admin-port";
    String ADMIN_PACKAGE_PROPERTY = "admin-package";

    @Key(ADMIN_PORT_PROPERTY)
    @DefaultValue("8080")
    int port();

    @Key(ADMIN_PACKAGE_PROPERTY)
    String packages();
}
