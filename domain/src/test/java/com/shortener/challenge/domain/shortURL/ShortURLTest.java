package com.shortener.challenge.domain.shortURL;

import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

public class ShortURLTest {
    @BeforeEach
    public void clean() {

    }

    @Test
    public void givenAValidParams_whenCallWith_thenInstantiateAShortURL() {
        final UUID expectedId = UUID.randomUUID();
        final int MIN_SIZE = 5;
        final int MAX_SIZE = 10;
        final String expectedCode = new ShortURLCodeLang3().create(MIN_SIZE, MAX_SIZE);
        final String expectedTarget = "www.test.com.br";
        final Instant expectedCreatedAt = Instant.now();
        final Instant expectedValidity = expectedCreatedAt.plusSeconds(60);

        final ShortURL actualShortURL = ShortURL.with(
                expectedId,
                expectedCode,
                expectedTarget,
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
        final String expectedTarget = "www.test.com.br";
        final int expectedSecondsInMemory = 90;
        final Instant createdAt = Instant.now();
        final int MIN_SIZE = 5;
        final int MAX_SIZE = 10;
        final ShortURL actualShortURL = ShortURL.newShortURL(expectedTarget, expectedSecondsInMemory, new ShortURLCodeLang3());

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
