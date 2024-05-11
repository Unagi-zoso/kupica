package org.nightdivers.kupica.domain.member;

import lombok.Getter;

@Getter
public enum SocialLoginType {
    KAKAO("KAKAO");

    private final String description;

    SocialLoginType(String description) {
        this.description = description;
    }

    public static SocialLoginType of(String provider) {
        return SocialLoginType.valueOf(provider.toUpperCase());
    }
}
