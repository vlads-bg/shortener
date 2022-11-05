package com.shortener.challenge.application.shorturl.retrieve.get.code;

import com.shortener.challenge.domain.shortURL.ShortURL;

public record GetByCodeShortURLOutput(String target, Boolean isValid) {
    public static GetByCodeShortURLOutput from(final ShortURL aShortURL) {
        return new GetByCodeShortURLOutput(aShortURL.getTarget(), aShortURL.isValid());
    }
}
