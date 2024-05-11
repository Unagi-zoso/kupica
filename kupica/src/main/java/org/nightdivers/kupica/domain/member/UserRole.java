package org.nightdivers.kupica.domain.member;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    MEMBER("MEMBER"),
    SIGNING_UP("SIGNING_UP"),
    ANONYMOUS("ANONYMOUS");

    private final String description;

    UserRole(String roleName) {
        this.description = roleName;
    }
}
