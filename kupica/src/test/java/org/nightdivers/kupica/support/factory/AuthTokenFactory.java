package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.domain.member.UserRole.ANONYMOUS;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestOAuth2User;

import org.springframework.security.authentication.TestingAuthenticationToken;

public class AuthTokenFactory {

    public static TestingAuthenticationToken createMemberTestAuthToken() {
        return new TestingAuthenticationToken(
                createTestOAuth2User(MEMBER),
                "member",
                MEMBER.getDescription()
        );
    }

    public static TestingAuthenticationToken createSigningUpTestAuthToken() {
        return new TestingAuthenticationToken(
                createTestOAuth2User(SIGNING_UP),
                "signing up",
                SIGNING_UP.getDescription()
        );
    }

    public static TestingAuthenticationToken createAnonymousTestAuthToken() {
        return new TestingAuthenticationToken(
                createTestOAuth2User(ANONYMOUS),
                "anonymous",
                ANONYMOUS.getDescription()
        );
    }
}
