package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestOAuth2User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.dto.MemberDto;

@DisplayNameGeneration(ReplaceUnderscores.class)
class AuthServiceTest {

    @Mock
    private MemberService memberService;
    @InjectMocks
    AuthService authService;

    @BeforeEach()
    public void setUp() {
        openMocks(this);
    }

    @Nested
    class 회원_가입_시 {

        @Nested
        class 유효한_회원정보가_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberService.append(any(MemberDto.class))).thenReturn(MemberDto.fromEntity(createTestMember1()));
            }

            @Test
            void 해당_회원을_추가한다() {
                assertEquals(
                        MemberDto.fromEntity(createTestMember1()), authService.signup(new RegisterRequest("TEST"), createTestOAuth2User(SIGNING_UP))
                );
                verify(memberService).append(any(MemberDto.class));
            }
        }

        @Nested
        class 회원_저장에_실패하는_경우 {

            @BeforeEach
            void context() {
                doThrow(new IllegalArgumentException()).when(memberService).append(any(MemberDto.class));
            }

            @Test
            void IllegalArgumentException_예외를_던진다() {
                assertThrows(
                        IllegalArgumentException.class,
                        () -> authService.signup(new RegisterRequest(TEST_INVALID_MEMBER_NICKNAME), createTestOAuth2User(MEMBER))
                );
            }
        }
    }
}
