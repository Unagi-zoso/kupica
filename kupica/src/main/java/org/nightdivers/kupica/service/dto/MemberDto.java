package org.nightdivers.kupica.service.dto;

import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.SocialLoginType;
import org.nightdivers.kupica.domain.member.UserRole;

public record MemberDto(
        Long id,
        String nickname,
        String email,
        UserRole role,
        String provider
) {

    public static MemberDto of(RegisterRequest request, CustomOAuth2User oAuth2User, UserRole role) {
        return new MemberDto(null, request.nickname(), oAuth2User.getEmail(), role, oAuth2User.getProvider());
    }

    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
                member.getId(), member.getNickname(), member.getEmailAddress(), member.getRole(),
                member.getSocialLoginType().getDescription()
        );
    }

    public Member toEntity() {
        return Member.of(id, nickname, email, role, SocialLoginType.of(provider));
    }
}
