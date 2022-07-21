package com.shortener.challenge.domain.shortURL;

import java.util.Optional;
import java.util.UUID;

public interface ShortURLGateway {
    ShortURL create(ShortURL aShortURL);
    void deleteById(UUID anId);
    Optional<ShortURL> findById(UUID anId);
    Optional<ShortURL> findByCode(String aCode);
}
