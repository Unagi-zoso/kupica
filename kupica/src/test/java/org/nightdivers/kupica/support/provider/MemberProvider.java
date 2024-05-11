package org.nightdivers.kupica.support.provider;

import java.util.List;

public class MemberProvider {

    public static List<String> invalidEmailAddressParameters() {
        return List.of(
                "abc",
                "abc@",
                "abc.com",
                "abc@com",
                "abc@.com", // 5
                "abc@com.",
                "abc@com..",
                "invalid@-com",
                "invalid@com-",
                "invalid@com.-com", // 10
                "invalid@test.c",
                "invalid@test.c-",
                "invalid@test.c-",
                "invalid-email",
                "invalid-email@", // 15
                "invalid-email.com",
                "invalid-email@com",
                "invalid-email@.com",
                "invalid-email@com.",
                "invalid-email@com.." // 20
        );
    }
}
