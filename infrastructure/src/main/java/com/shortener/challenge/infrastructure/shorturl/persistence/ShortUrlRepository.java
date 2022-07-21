package com.shortener.challenge.infrastructure.shorturl.persistence;

import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface ShortUrlRepository extends JpaRepository<ShortUrlJpaEntity, String>{

    @Lock(LockModeType.READ)
    Optional<ShortUrlJpaEntity> findByCode(String code);
}
