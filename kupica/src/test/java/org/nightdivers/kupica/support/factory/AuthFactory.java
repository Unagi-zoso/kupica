package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_NICKNAME;

import org.nightdivers.kupica.service.dto.KakaoLoginRequest;
import org.nightdivers.kupica.service.dto.KakaoRegisterRequest;
import org.nightdivers.kupica.service.dto.KakaoUserInfo;

public class AuthFactory {

    public static final String TEST_OAUTH2_ACCESS_TOKEN = "OAuth2AccessToken";
    public static final String TEST_OAUTH2_ID = "1234";
    public static final String TEST_KAKAO_UID = "KAKAO_1234";

    public static KakaoLoginRequest createKakaoLoginRequest() {
        return new KakaoLoginRequest(TEST_OAUTH2_ACCESS_TOKEN);
    }

    public static KakaoUserInfo createKakaoUserInfo() {
        return new KakaoUserInfo(TEST_OAUTH2_ID, TEST_MEMBER_1_EMAIL);
    }

    public static KakaoRegisterRequest createKakaoRegisterRequest() {
        return new KakaoRegisterRequest(TEST_KAKAO_UID, TEST_MEMBER_1_EMAIL, TEST_MEMBER_1_NICKNAME);
    }
}
