package org.nightdivers.kupica.support.provider;

import java.util.List;

public class MemberProvider {
    public static List<String> invalidEmailAddressParameters() {
        return List.of(
                "abc",
                "abc@",
                "abc.com",
                "abc@com",
                "abc@.com",
                "abc@com.",
                "abc@com..",
                "invalid@-com",
                "invalid@com-",
                "invalid@com-.com",
                "invalid@com.-com",
                "invalid@test..com",
                "invalid@test.c",
                "invalid@test.co",
                "invalid@test.c-",
                "invalid@test.c-",
                "invalid-email",
                "invalid-email@",
                "invalid-email.com",
                "invalid-email@com",
                "invalid-email@.com",
                "invalid-email@com.",
                "invalid-email@com.."
        );
    }
}
