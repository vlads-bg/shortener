package com.shortener.challenge.application.shorturl.retrieve.get.code;

import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetByCodeShortURLUseCase extends GetByCodeShortURLUseCase {

    private final ShortURLGateway shortURLGateway;
    private final ShortURLCode shortURLCode;

    DefaultGetByCodeShortURLUseCase(
        ShortURLGateway shortURLGateway,
        ShortURLCode shortURLCode
    ) {
        this.shortURLGateway = Objects.requireNonNull(shortURLGateway);
        this.shortURLCode = Objects.requireNonNull(shortURLCode);
    }

    public GetByCodeShortURLOutput execute(String aCode) {
       return this.shortURLGateway.findByCode(aCode)
           .map(GetByCodeShortURLOutput::from)
           .orElseThrow(notFound(aCode));
    }

    private Supplier<NotFoundException> notFound(final String aCode) {
        return () -> NotFoundException.with(ShortURL.class, aCode);
    }
}
