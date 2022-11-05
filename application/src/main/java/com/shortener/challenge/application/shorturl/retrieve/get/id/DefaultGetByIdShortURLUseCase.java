package com.shortener.challenge.application.shorturl.retrieve.get.id;

import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;
import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class DefaultGetByIdShortURLUseCase extends GetByIdShortURLUseCase {
    @NonNull
    private final ShortURLGateway shortURLGateway;

    public GetByIdShortURLOutput execute(UUID anId) {
        return this.shortURLGateway.findById(anId)
                .map(GetByIdShortURLOutput::from)
                .orElseThrow(notFound(anId));
    }

    private Supplier<NotFoundException> notFound(final UUID anId) {
        return () -> NotFoundException.with(ShortURL.class, anId);
    }
}
