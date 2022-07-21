package com.shortener.challenge.application.shorturl.retrieve.get.id;

import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class DefaultGetByIdShortURLUseCase extends GetByIdShortURLUseCase {

    private final ShortURLGateway shortURLGateway;
    private final ShortURLCode shortURLCode;

    DefaultGetByIdShortURLUseCase(
        ShortURLGateway shortURLGateway,
        ShortURLCode shortURLCode
    ) {
        this.shortURLGateway = Objects.requireNonNull(shortURLGateway);
        this.shortURLCode = Objects.requireNonNull(shortURLCode);
    }

    public GetByIdShortURLOutput execute(UUID anId) {
       return this.shortURLGateway.findById(anId)
           .map(GetByIdShortURLOutput::from)
           .orElseThrow(notFound(anId));
    }

    private Supplier<NotFoundException> notFound(final UUID anId) {
        return () -> NotFoundException.with(ShortURL.class, anId);
    }
}
