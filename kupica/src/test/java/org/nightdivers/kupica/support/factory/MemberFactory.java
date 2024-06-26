package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_NICKNAME;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_ROLE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_2_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_2_NICKNAME;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_2_ROLE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_2_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_3_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_3_NICKNAME;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_3_ROLE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_3_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_4_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_4_NICKNAME;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_4_ROLE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_4_SOCIAL_LOGIN_TYPE;

import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.SocialLoginType;
import org.nightdivers.kupica.domain.member.UserRole;

public class MemberFactory {

    public static Member createTestMember1() {
        return Member.of(
                TEST_MEMBER_1_NICKNAME,
                TEST_MEMBER_1_EMAIL,
                TEST_MEMBER_1_ROLE,
                TEST_MEMBER_1_SOCIAL_LOGIN_TYPE
        );
    }

    public static Member createTestMember2() {
        return Member.of(
                TEST_MEMBER_2_NICKNAME,
                TEST_MEMBER_2_EMAIL,
                TEST_MEMBER_2_ROLE,
                TEST_MEMBER_2_SOCIAL_LOGIN_TYPE
        );
    }

    public static Member createTestMember3() {
        return Member.of(
                TEST_MEMBER_3_NICKNAME,
                TEST_MEMBER_3_EMAIL,
                TEST_MEMBER_3_ROLE,
                TEST_MEMBER_3_SOCIAL_LOGIN_TYPE
        );
    }

    public static Member createTestMember4() {
        return Member.of(
                TEST_MEMBER_4_NICKNAME,
                TEST_MEMBER_4_EMAIL,
                TEST_MEMBER_4_ROLE,
                TEST_MEMBER_4_SOCIAL_LOGIN_TYPE
        );
    }

    public static Member createCustomMember(String nickname, String email) {
        return Member.of(
                nickname,
                email,
                UserRole.MEMBER,
                SocialLoginType.KAKAO
        );
    }
}
