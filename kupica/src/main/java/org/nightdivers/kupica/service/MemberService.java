package org.nightdivers.kupica.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean isExist(String email) {
        return memberRepository.existsByEmailAddressAndErasedFlagIsFalse(email);
    }

    @Transactional
    public MemberDto append(MemberDto memberDto) {
        return MemberDto.fromEntity(memberRepository.save(memberDto.toEntity()));
    }

    public MemberDto getMemberByEmail(String email) {
        return MemberDto.fromEntity(
                memberRepository.findByEmailAddressAndErasedFlagIsFalse(email).orElseThrow(NoSuchElementException::new)
        );
    }

    @Transactional
    public MemberDto updateNickname(String newNickname, String email) {
        Member member = getMemberByEmail(email).toEntity();
        member.changeNickname(newNickname);
        return MemberDto.fromEntity(memberRepository.save(member));
    }

    @Transactional
    public void remove(String email) {
        Member member = getMemberByEmail(email).toEntity();
        member.erase();
        memberRepository.save(member);
    }
}
