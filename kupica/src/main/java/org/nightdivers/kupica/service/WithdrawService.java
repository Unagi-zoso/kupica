package org.nightdivers.kupica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WithdrawService {

    private final MemberService memberService;

    @Transactional
    public void withdrawUser(String email) {
        memberService.remove(email);
    }
}
