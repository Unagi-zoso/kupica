package org.nightdivers.kupica.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.service.WithdrawService;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final WithdrawService withdrawService;

    @DeleteMapping("/withdraw")
    public ResponseEntity<Void> withdraw(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        CustomOAuth2User member = (CustomOAuth2User) authentication.getPrincipal();
        withdrawService.withdrawUser(member.getEmail());
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return ResponseEntity.ok().build();
    }
}
