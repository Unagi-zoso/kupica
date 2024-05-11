package org.nightdivers.kupica.service.dto;

import org.nightdivers.kupica.domain.member.SocialLoginType;

public interface OAuth2Response {

    SocialLoginType getProvider();

    String getProviderId();

    String getEmail();
}
