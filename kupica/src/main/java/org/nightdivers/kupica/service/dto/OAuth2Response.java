package org.nightdivers.kupica.service.dto;

import java.util.Map;
import org.nightdivers.kupica.domain.member.SocialLoginType;

public interface OAuth2Response {

    Map<String, Object> getAttributes();

    SocialLoginType getProvider();

    String getProviderId();

    String getEmail();
}
