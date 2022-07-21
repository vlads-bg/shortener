package com.shortener.challenge.domain.shortURL;

import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import com.shortener.challenge.domain.shortURL.telemetry.ServiceTelemetry;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ShortURL implements Cloneable {
    private final UUID id;
    private final String code;
    private final String target;
    private final boolean active;
    private final Instant validity;
    private final Instant createdAt;

    static final private Integer SECONDS_IN_MEMORY = 60;
    static final private Integer MIN_LENGTH = 5;
    static final private Integer MAX_LENGTH = 10;
    private ShortURL(
        final UUID id,
        final String code,
        final String target,
        final boolean active,
        final Instant validity,
        final Instant createdAt
    ) {
        this.id = id;
        this.code = Objects.requireNonNull(code, "'code' should not be null");;
        this.target = Objects.requireNonNull(target, "'target' should not be null");;
        this.active = active;
        this.validity = Objects.requireNonNull(validity, "'validity' should not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
    }

    public static ShortURL newShortURL(
        final String target,
        final Integer secondsInMemory,
        final ShortURLCode shortURLCode,
        final ServiceTelemetry serviceTelemetry
    ) {
        final UUID id = UUID.randomUUID();
        final String code = shortURLCode.create(MIN_LENGTH, MAX_LENGTH);

        final Instant now = Instant.now();
        final Instant validity = now.plusSeconds(secondsInMemory != null ? secondsInMemory : SECONDS_IN_MEMORY);

//        serviceTelemetry.register(new Telemetry());
        return with(id, code, target, true, validity, now);
    }

    public static ShortURL with(
        final UUID id,
        final String code,
        final String target,
        final boolean active,
        final Instant validity,
        final Instant createdAt
    ) {
        return new ShortURL(id, code, target, active, validity, createdAt);
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTarget() {
        return target;
    }

    public boolean isActive() {return active;}

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Boolean isValid() {
        return Instant.now().isBefore(this.validity);
    }

    public Instant getValidity() {
        return validity;
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
