package org.nightdivers.kupica.controller.dto;

import org.nightdivers.kupica.service.dto.MemberDto;

public record MemberResponse(
        String nickname,
        String email,
        String provider
) {

    public static MemberResponse of(String nickname, String email, String provider) {
        return new MemberResponse(nickname, email, provider);
    }

    public static MemberResponse fromMemberDto(MemberDto memberDto) {
        return new MemberResponse(memberDto.nickname(), memberDto.email(), memberDto.provider());
    }
}
