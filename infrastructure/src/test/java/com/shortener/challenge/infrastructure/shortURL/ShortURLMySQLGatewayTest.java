package com.shortener.challenge.infrastructure.shortURL;

import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import com.shortener.challenge.infrastructure.shorturl.ShortURLMySQLGateway;
import com.shortener.challenge.infrastructure.shorturl.persistence.ShortUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShortURLMySQLGatewayTest {

    @Autowired
    private ShortURLMySQLGateway gateway;

    @Autowired
    private ShortUrlRepository repository;

    @Test
    public void givenAValidShortURL_whenCallsCreate_shouldReturnANewShortURL() {
        final String expectedTarget = "www.mercadolivre.com";

        ShortURLCode shortURLCode = new ShortURLCodeLang3();
        final ShortURL aShortURL = ShortURL.newShortURL(expectedTarget, 90, shortURLCode, null);

        Assertions.assertEquals(0, repository.count());

        final ShortURL actualShortURL = gateway.create(aShortURL);

        Assertions.assertEquals(1, repository.count());

//        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
//        Assertions.assertEquals(expectedName, actualCategory.getName());
//        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
//        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
//        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
//        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
//        Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.getDeletedAt());
//        Assertions.assertNull(actualCategory.getDeletedAt());
//
//        final var actualEntity = categoryRepository.findById(aCategory.getId().getValue()).get();
//
//        Assertions.assertEquals(aCategory.getId().getValue(), actualEntity.getId());
//        Assertions.assertEquals(expectedName, actualEntity.getName());
//        Assertions.assertEquals(expectedDescription, actualEntity.getDescription());
//        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
//        Assertions.assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
//        Assertions.assertEquals(aCategory.getUpdatedAt(), actualEntity.getUpdatedAt());
//        Assertions.assertEquals(aCategory.getDeletedAt(), actualEntity.getDeletedAt());
//        Assertions.assertNull(actualEntity.getDeletedAt());
    }

}
