package org.nightdivers.kupica.controller;

import static org.springframework.http.HttpStatus.FOUND;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.controller.dto.MemberResponse;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.AuthService;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MemberResponse> register(
            RegisterRequest request, @AuthenticationPrincipal CustomOAuth2User oAuth2User, HttpServletRequest httpRequest
    ) {
        httpRequest.getSession().invalidate();
        return ResponseEntity.status(FOUND)
                .header("Location", "/login")
                .body(MemberResponse.fromMemberDto(authService.signup(request, oAuth2User)));
    }
}
