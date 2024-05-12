package org.nightdivers.kupica.support.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class JsonParserMethodProvider {

    private final static Gson gson = new Gson();

    public static <T> T parseMapFromString(String json, Class<T> typeClazz) {
        return gson.fromJson(json, provideToken(typeClazz));
    }

    private static <T> Type provideToken(Class<T> typeClazz) {
        return TypeToken.getParameterized(typeClazz).getType();
    }
}
