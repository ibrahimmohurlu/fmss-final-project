package com.ibrahimmohurlu.package_service.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthorizationUtils {

    public static String getUsernameFromAuthHeader(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);


        final String[] values = credentials.split(":", 2);
        String username = values[0];
        String password = values[1];

        return username;
    }
}
