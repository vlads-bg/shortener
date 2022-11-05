package com.shortener.challenge.infrastructure.shorturl.models;

public record ShortUrlResponse(String code, String target) {

    public static ShortUrlResponse with(final String code, final String target) {
        return new ShortUrlResponse(code, target);
    }
}
