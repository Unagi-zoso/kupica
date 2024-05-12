package org.nightdivers.kupica.service;

import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean isExist(String email) {
        return memberRepository.existsByEmailAddress(email);
    }

    public MemberDto save(MemberDto memberDto) {
        return MemberDto.fromEntity(memberRepository.save(memberDto.toEntity()));
    }
}
