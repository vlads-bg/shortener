package com.shortener.challenge.application.shorturl.create;

import com.shortener.challenge.domain.shortURL.ShortURL;

public record CreateShortURLOutput(String target, String code) {
    public static CreateShortURLOutput from(final ShortURL aShortURL) {
        return new CreateShortURLOutput(aShortURL.getTarget(), aShortURL.getCode());
    }
}
