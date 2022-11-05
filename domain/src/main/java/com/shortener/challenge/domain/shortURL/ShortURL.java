package com.shortener.challenge.domain.shortURL;

import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ShortURL implements Cloneable {
    private final UUID id;
    private final String code;
    private final String target;
    private final Instant validity;
    private final Instant createdAt;

    static final private Integer DEFAULT_SECONDS_IN_MEMORY = 60;
    static final private Integer MIN_LENGTH = 5;
    static final private Integer MAX_LENGTH = 10;

    public static ShortURL newShortURL(
            final String target,
            final Integer secondsInMemory,
            final ShortURLCode shortURLCode
    ) {
        final UUID id = UUID.randomUUID();
        final String code = shortURLCode.create(MIN_LENGTH, MAX_LENGTH);

        final Instant now = Instant.now();
        final Instant validity = now.plusSeconds(secondsInMemory != null ? secondsInMemory : DEFAULT_SECONDS_IN_MEMORY);

        return with(id, code, target, validity, now);
    }

    public static ShortURL with(
            final UUID id,
            final String code,
            final String target,
            final Instant validity,
            final Instant createdAt
    ) {
        return new ShortURL(id, code, target, validity, createdAt);
    }

    public Boolean isValid() {
        return Instant.now().isBefore(this.validity);
    }

    @Override
    public ShortURL clone() {
        try {
            return (ShortURL) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
