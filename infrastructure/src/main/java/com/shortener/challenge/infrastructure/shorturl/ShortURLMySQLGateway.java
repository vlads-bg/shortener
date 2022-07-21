package com.shortener.challenge.infrastructure.shorturl;

import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.infrastructure.shorturl.persistence.ShortUrlJpaEntity;
import com.shortener.challenge.infrastructure.shorturl.persistence.ShortUrlRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShortURLMySQLGateway implements ShortURLGateway {
    private final ShortUrlRepository repository;

    public ShortURLMySQLGateway(ShortUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShortURL create(ShortURL aShortURL) {
        return save(aShortURL);
    }

    @Override
    public void deleteById(final UUID anId) {
        final String anIdValue = anId.toString();
        if(this.repository.existsById(anIdValue)){
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<ShortURL> findById(UUID anId) {
        return this.repository.findById(anId.toString())
            .map(ShortUrlJpaEntity::toAgregate);
    }

    @Override
    public Optional<ShortURL> findByCode(String aCode) {
        return this.repository.findByCode(aCode)
            .map(ShortUrlJpaEntity::toAgregate);
    }

    private ShortURL save (final ShortURL shortURL) {
        return this.repository.save(ShortUrlJpaEntity.from(shortURL)).toAgregate();
    }
}
