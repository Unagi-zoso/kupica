package org.nightdivers.kupica.service;

import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;

import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberService memberService;

    @Transactional
    public MemberDto signup(RegisterRequest request, CustomOAuth2User oAuth2User) {
        return memberService.append(MemberDto.of(request, oAuth2User, MEMBER));
    }
}
