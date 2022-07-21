package com.shortener.challenge.application.shorturl.retrieve.get.id;

import com.shortener.challenge.domain.shortURL.ShortURL;
import java.time.Instant;
import java.util.UUID;

public class GetByIdShortURLOutput {

    private final UUID id;
    private final String code;
    private final String target;
    private final Instant validity;
    private final Instant createdAt;

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTarget() {
        return target;
    }

    public Instant getValidity() {
        return validity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    GetByIdShortURLOutput(
        final UUID id,
        final String target,
        final String code,
        final Instant validity,
        final Instant createdAt
    ) {
        this.id = id;
        this.target = target;
        this.code = code;
        this.validity = validity;
        this.createdAt = createdAt;
    }

    public static GetByIdShortURLOutput from(final ShortURL aShortURL) {
        return new GetByIdShortURLOutput(
            aShortURL.getId(),
            aShortURL.getTarget(),
            aShortURL.getCode(),
            aShortURL.getValidity(),
            aShortURL.getCreatedAt());
    }
}
