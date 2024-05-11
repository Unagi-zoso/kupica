package org.nightdivers.kupica.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.service.MemberService;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        CustomOAuth2User userDetails = (CustomOAuth2User) authentication.getPrincipal();

        if (userExistsInDatabase(userDetails.getEmail())) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }

    private boolean userExistsInDatabase(String email) {
        return memberService.isExist(email);
    }
}
