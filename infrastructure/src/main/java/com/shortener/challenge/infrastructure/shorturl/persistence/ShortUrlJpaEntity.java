package com.shortener.challenge.infrastructure.shorturl.persistence;

import com.shortener.challenge.domain.shortURL.ShortURL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "ShortUrl")
@Table(name = "short_url")
public class ShortUrlJpaEntity {
    @Id
    private String id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "validity", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant validity;

    public ShortUrlJpaEntity() {
    }

    private ShortUrlJpaEntity(
            final UUID id,
            final String code,
            final String target,
            final Instant createdAt,
            final Instant validity
    ) {
        this.id = id.toString();
        this.code = code;
        this.target = target;
        this.createdAt = createdAt;
        this.validity = validity;
    }

    public static ShortUrlJpaEntity from(final ShortURL shortURL) {
        return new ShortUrlJpaEntity(
                shortURL.getId(),
                shortURL.getCode(),
                shortURL.getTarget(),
                shortURL.getCreatedAt(),
                shortURL.getValidity()
        );
    }

    public ShortURL toAgregate() {
        return ShortURL.with(
                UUID.fromString(getId()),
                getCode(),
                getTarget(),
                getCreatedAt(),
                getValidity()
        );
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTarget() {
        return target;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getValidity() {
        return validity;
    }
}
