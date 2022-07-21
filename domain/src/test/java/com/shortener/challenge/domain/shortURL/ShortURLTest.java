package com.shortener.challenge.domain.shortURL;

import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShortURLTest {
    @BeforeEach
    private void clean() {

    }

    @Test
    public void givenAValidParams_whenCallWith_thenInstantiateAShortURL() {
        final UUID expectedId = UUID.randomUUID();
        final int MIN_SIZE = 5;
        final int MAX_SIZE = 10;
        final String expectedCode = new ShortURLCodeLang3().create(MIN_SIZE, MAX_SIZE);
        final String expectedTarget = "www.mercadolivre.com.br";
        final boolean expectedIsActive = true;
        final Instant now = Instant.now();
        final Instant expectedCreatedAt = now;
        final Instant expectedValidity = now.plusSeconds(60);

        final ShortURL actualShortURL = ShortURL.with(
            expectedId,
            expectedCode,
            expectedTarget,
            expectedIsActive,
            expectedValidity,
            expectedCreatedAt
        );

        Assertions.assertNotNull(actualShortURL);
        Assertions.assertEquals(expectedId, actualShortURL.getId());
        Assertions.assertEquals(expectedCode, actualShortURL.getCode());
        Assertions.assertEquals(expectedTarget, actualShortURL.getTarget());
        Assertions.assertTrue(actualShortURL.isValid());
        Assertions.assertEquals(expectedCreatedAt, actualShortURL.getCreatedAt());
    }

    @Test
    public void givenAValidParams_whenCallNewShortURL_thenInstantiateAShortURL() {
        final String expectedTarget = "www.mercadolivre.com.br";
        final Integer expectedSecondsInMemory = 90;
        final Instant createdAt = Instant.now();
        final int MIN_SIZE = 5;
        final int MAX_SIZE = 10;
        final ShortURL actualShortURL = ShortURL.newShortURL(expectedTarget, expectedSecondsInMemory, new ShortURLCodeLang3(), null);

        final Instant expectedValidity = actualShortURL.getCreatedAt().plusSeconds(expectedSecondsInMemory);

        Assertions.assertNotNull(actualShortURL);
        Assertions.assertNotNull(actualShortURL.getId());
        Assertions.assertEquals(expectedTarget, actualShortURL.getTarget());
        Assertions.assertEquals(expectedValidity, actualShortURL.getValidity());
        Assertions.assertTrue(actualShortURL.isValid());
        Assertions.assertTrue(actualShortURL.getCode().length() >= MIN_SIZE);
        Assertions.assertTrue(actualShortURL.getCode().length() <= MAX_SIZE);
        Assertions.assertTrue(actualShortURL.getValidity().isAfter(createdAt));
    }
}
