package org.nightdivers.kupica.service.dto;

import static org.nightdivers.kupica.domain.member.SocialLoginType.KAKAO;

import java.util.Map;
import org.nightdivers.kupica.domain.member.SocialLoginType;

public class KakaoResponse implements OAuth2Response {
    private final Map<String, Object> attributes;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public SocialLoginType getProvider() {
        return KAKAO;
    }

    @Override
    public String getProviderId() {
        return KAKAO.getDescription();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }
}
