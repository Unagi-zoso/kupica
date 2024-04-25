package org.nightdivers.kupica.support.factory;

import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_PASSWORD;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_ROLE;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_PASSWORD;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_ROLE;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_3_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_3_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_3_PASSWORD;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_3_ROLE;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_4_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_4_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_4_PASSWORD;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_4_ROLE;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.member.UserRole;

public class AnonymousUserFactory {
    public static AnonymousUser createTestAnonymousUser1() {
        return AnonymousUser.of(
                TEST_ANONYMOUS_USER_1_NICKNAME,
                TEST_ANONYMOUS_USER_1_PASSWORD,
                TEST_ANONYMOUS_USER_1_IP_ADDRESS,
                TEST_ANONYMOUS_USER_1_ROLE
        );
    }

    public static AnonymousUser createTestAnonymousUser2() {
        return AnonymousUser.of(
                TEST_ANONYMOUS_USER_2_NICKNAME,
                TEST_ANONYMOUS_USER_2_PASSWORD,
                TEST_ANONYMOUS_USER_2_IP_ADDRESS,
                TEST_ANONYMOUS_USER_2_ROLE
        );
    }

    public static AnonymousUser createTestAnonymousUser3() {
        return AnonymousUser.of(
                TEST_ANONYMOUS_USER_3_NICKNAME,
                TEST_ANONYMOUS_USER_3_PASSWORD,
                TEST_ANONYMOUS_USER_3_IP_ADDRESS,
                TEST_ANONYMOUS_USER_3_ROLE
        );
    }

    public static AnonymousUser createTestAnonymousUser4() {
        return AnonymousUser.of(
                TEST_ANONYMOUS_USER_4_NICKNAME,
                TEST_ANONYMOUS_USER_4_PASSWORD,
                TEST_ANONYMOUS_USER_4_IP_ADDRESS,
                TEST_ANONYMOUS_USER_4_ROLE
        );
    }

    public static List<AnonymousUser> createDuplicatedAnonymousUsers(Supplier<AnonymousUser> creatorMethod, int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> creatorMethod.get())
                .collect(Collectors.toList());
    }

    public static AnonymousUser createCustomAnonymousUsers(String nickname, String password, String ipAddress) {
        return AnonymousUser.of(
                nickname,
                password,
                ipAddress,
                UserRole.ANONYMOUS
        );
    }
}
