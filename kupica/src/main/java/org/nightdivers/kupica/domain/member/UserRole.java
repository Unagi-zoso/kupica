package org.nightdivers.kupica.domain.member;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String description;

    UserRole(String roleName) {
        this.description = roleName;
    }
}