package com.shortener.challenge.domain.shortURL.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShortURLCodeLang3Test {
    @Test
    public void givenAValidParams_whenCallCreate_thenCreatedACode() {
        final Integer minLength = 5;
        final Integer maxLength = 10;
        final ShortURLCodeLang3 shortURLCodeLang3 = new ShortURLCodeLang3();
        String actualCode = shortURLCodeLang3.create(minLength, maxLength);
        Assertions.assertNotNull(actualCode);
        Assertions.assertTrue(actualCode.length() >= minLength);
        Assertions.assertTrue(actualCode.length() <= maxLength);
    }
}
