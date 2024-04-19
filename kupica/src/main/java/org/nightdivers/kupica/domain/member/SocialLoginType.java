package org.nightdivers.kupica.domain.member;

import lombok.Getter;

@Getter
public enum SocialLoginType {
    KAKAO("KAKAO");

    private final String description;

    SocialLoginType(String description) {
        this.description = description;
    }
}
