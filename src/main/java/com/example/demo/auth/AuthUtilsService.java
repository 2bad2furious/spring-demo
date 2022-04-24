package com.example.demo.auth;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUtilsService {
    private static final String COOKIE_NAME = "auth";

    public Optional<UUID> get(@NotNull HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(COOKIE_NAME))
                .map(c -> tryParseUUID(c.getValue()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public void add(@NotNull HttpServletResponse response, @NotNull UUID uuid) {
        response.addCookie(new Cookie(COOKIE_NAME, uuid.toString()));
    }

    public static Optional<UUID> tryParseUUID(@NotNull String value) {
        try {
            return Optional.of(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
