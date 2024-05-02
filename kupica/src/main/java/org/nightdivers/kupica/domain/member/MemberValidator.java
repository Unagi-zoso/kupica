package org.nightdivers.kupica.domain.member;

public class MemberValidator {
    public static String validateNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력값입니다.");
        }

        if (nickname.length() < 2 || nickname.length() > 18) {
            throw new IllegalArgumentException("닉네임은 2자 이상 18자 이하로 입력해주세요.");
        }

        return nickname;
    }

    public static String validateEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("이메일 주소는 필수 입력값입니다.");
        }

        if (emailAddress.length() < 6 || emailAddress.length() > 255) {
            throw new IllegalArgumentException("이메일 주소는 6자 이상 255자 이하로 입력해주세요.");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!emailAddress.matches(emailRegex)) {
            throw new IllegalArgumentException("이메일 주소 형식이 올바르지 않습니다.");
        }

        return emailAddress;
    }
}
