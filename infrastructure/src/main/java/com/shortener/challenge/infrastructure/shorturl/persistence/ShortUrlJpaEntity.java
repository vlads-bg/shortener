package com.shortener.challenge.infrastructure.shorturl.persistence;

import com.shortener.challenge.domain.shortURL.ShortURL;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ShortUrl")
@Table(name="short_url")
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

    @Column(name = "active", nullable = false)
    private boolean active;

    public ShortUrlJpaEntity() {
    }

    private ShortUrlJpaEntity (
        final UUID id,
        final String code,
        final String target,
        final Instant createdAt,
        final Instant validity,
        final boolean active
    ) {
        this.id = id.toString();
        this.code = code;
        this.target = target;
        this.createdAt = createdAt;
        this.validity = validity;
        this.active = active;
    }

    public static ShortUrlJpaEntity from(final ShortURL shortURL) {
        return new ShortUrlJpaEntity(
            shortURL.getId(),
            shortURL.getCode(),
            shortURL.getTarget(),
            shortURL.getCreatedAt(),
            shortURL.getValidity(),
            shortURL.isActive()
        );
    }

    public ShortURL toAgregate() {
        return ShortURL.with(
            UUID.fromString(getId()),
            getCode(),
            getTarget(),
            isActive(),
            getCreatedAt(),
            getValidity()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public Instant getValidity() {
        return validity;
    }

    public void setValiity(Instant validity) {
        this.validity = validity;
    }
}
