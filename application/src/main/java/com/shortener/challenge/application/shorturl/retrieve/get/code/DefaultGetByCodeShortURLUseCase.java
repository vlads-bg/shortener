package com.shortener.challenge.application.shorturl.retrieve.get.code;

import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.function.Supplier;

@AllArgsConstructor
public class DefaultGetByCodeShortURLUseCase extends GetByCodeShortURLUseCase {
    @NonNull
    private final ShortURLGateway shortURLGateway;

    public GetByCodeShortURLOutput execute(String aCode) {
        return this.shortURLGateway.findByCode(aCode)
                .map(GetByCodeShortURLOutput::from)
                .orElseThrow(notFound(aCode));
    }

    private Supplier<NotFoundException> notFound(final String aCode) {
        return () -> NotFoundException.with(ShortURL.class, aCode);
    }
}
