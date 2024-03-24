package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CLIENT_READ("restaurant:read"),
    CLIENT_UPDATE("restaurant:update"),
    CLIENT_CREATE("restaurant:create"),
    CLIENT_DELETE("restaurant:delete")

    ;

    @Getter
    private final String permission;
}
