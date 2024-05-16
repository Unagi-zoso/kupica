package org.nightdivers.kupica.service;

import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.domain.member.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WithdrawService {

    private final MemberService memberService;

    @Transactional
    public void withdrawUser(String email, UserRole role) {
        if (role.equals(UserRole.MEMBER)) {
            memberService.remove(email);
        } else {
            throw new IllegalArgumentException("회원탈퇴할 권한이 없습니다.");
        }
    }
}
