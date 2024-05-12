package org.nightdivers.kupica.support.provider;

import static org.nightdivers.kupica.support.factory.AuthTokenFactory.createAnonymousTestAuthToken;
import static org.nightdivers.kupica.support.factory.AuthTokenFactory.createMemberTestAuthToken;
import static org.nightdivers.kupica.support.factory.AuthTokenFactory.createSigningUpTestAuthToken;

import org.springframework.security.core.context.SecurityContextHolder;

public class TestAuthenticationApplier {


    public static void applyMemberAuth() {
        SecurityContextHolder.getContext().setAuthentication(createMemberTestAuthToken());
    }

    public static void applySigningUpAuth() {
        SecurityContextHolder.getContext().setAuthentication(createSigningUpTestAuthToken());
    }

    public static void applyAnonymousAuth() {
        SecurityContextHolder.getContext().setAuthentication(createAnonymousTestAuthToken());
    }
}

