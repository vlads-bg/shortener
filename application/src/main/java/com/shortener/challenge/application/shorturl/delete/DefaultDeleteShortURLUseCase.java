package com.shortener.challenge.application.shorturl.delete;

import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import java.util.Objects;
import java.util.UUID;

public class DefaultDeleteShortURLUseCase extends DeleteShortURLUseCase {
    private final ShortURLGateway shortURLGateway;

    public DefaultDeleteShortURLUseCase(ShortURLGateway shortURLGateway) {
        this.shortURLGateway = Objects.requireNonNull(shortURLGateway);
    }

    public void execute(UUID anId) {
        this.shortURLGateway.deleteById(anId);
    }
}
