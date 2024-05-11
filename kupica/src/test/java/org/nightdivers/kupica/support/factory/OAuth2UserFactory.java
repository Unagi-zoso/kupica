package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;

import java.util.HashMap;
import org.nightdivers.kupica.domain.member.SocialLoginType;
import org.nightdivers.kupica.domain.member.UserRole;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.OAuth2Response;

public class OAuth2UserFactory {

    public static CustomOAuth2User createTestOAuth2User(UserRole role) {
        return new CustomOAuth2User(new TestOAuth2Response(), role);
    }

    private static HashMap<String, Object> createTestAttributesForKakao(String email) {
        HashMap<String, Object> outerAttributes = new HashMap<>();
        HashMap<String, Object> innerAttributes = new HashMap<>();
        innerAttributes.put("email", email);
        outerAttributes.put("kakao_account", innerAttributes);
        return outerAttributes;
    }

    public static HashMap<String, Object> createTestMemberAttributes() {
        return createTestAttributesForKakao(TEST_VALID_MEMBER_EMAIL);
    }

    public static HashMap<String, Object> createTestNotMemberAttributes() {
        return createTestAttributesForKakao(TEST_INVALID_MEMBER_EMAIL);
    }


    static class TestOAuth2Response implements OAuth2Response {

        @Override
        public SocialLoginType getProvider() {
            return SocialLoginType.KAKAO;
        }

        @Override
        public String getProviderId() {
            return "TEST";
        }

        @Override
        public String getEmail() {
            return TEST_VALID_MEMBER_EMAIL;
        }
    }
}
