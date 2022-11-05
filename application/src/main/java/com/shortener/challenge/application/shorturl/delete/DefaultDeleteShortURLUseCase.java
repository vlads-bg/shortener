package com.shortener.challenge.application.shorturl.delete;

import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
public class DefaultDeleteShortURLUseCase extends DeleteShortURLUseCase {
    @NonNull
    private final ShortURLGateway shortURLGateway;

    public void execute(UUID anId) {
        this.shortURLGateway.deleteById(anId);
    }
}
