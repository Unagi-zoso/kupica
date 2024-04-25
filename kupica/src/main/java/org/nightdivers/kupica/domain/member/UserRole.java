package org.nightdivers.kupica.domain.member;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private final String description;

    UserRole(String roleName) {
        this.description = roleName;
    }
}
