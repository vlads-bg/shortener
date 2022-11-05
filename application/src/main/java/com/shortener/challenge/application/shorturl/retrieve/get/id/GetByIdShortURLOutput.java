package com.shortener.challenge.application.shorturl.retrieve.get.id;

import com.shortener.challenge.domain.shortURL.ShortURL;

import java.time.Instant;
import java.util.UUID;

public record GetByIdShortURLOutput(UUID id, String code, String target, Instant validity, Instant createdAt) {
    public static GetByIdShortURLOutput from(final ShortURL aShortURL) {
        return new GetByIdShortURLOutput(aShortURL.getId(), aShortURL.getTarget(), aShortURL.getCode(),
                aShortURL.getValidity(), aShortURL.getCreatedAt());
    }
}
