package org.nightdivers.kupica.service.dto;

import java.util.Collection;
import java.util.Map;
import org.nightdivers.kupica.domain.member.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;

    private final UserRole userRole;

    public CustomOAuth2User(OAuth2Response oAuth2Response, UserRole userRole) {
        this.oAuth2Response = oAuth2Response;
        this.userRole = userRole;
    }

    public CustomOAuth2User(OAuth2Response oAuth2Response, UserRole userRole, String nickname) {
        this.oAuth2Response = oAuth2Response;
        this.userRole = userRole;
        oAuth2Response.getAttributes().put("nickname", nickname);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Response.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(userRole.getDescription());
    }

    @Override
    public String getName() {
        if (getAttributes().containsKey("name")) {
            return getAttributes().get("name").toString();
        } else {
            return getProvider() + getAttributes().get("email").toString();
        }
    }

    public String getProvider() {
        return oAuth2Response.getProvider().name();
    }

    public String getEmail() {
        return oAuth2Response.getEmail();
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
