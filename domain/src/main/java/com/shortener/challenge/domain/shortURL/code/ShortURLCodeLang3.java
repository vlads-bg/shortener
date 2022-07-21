package com.shortener.challenge.domain.shortURL.code;

import org.apache.commons.lang3.RandomStringUtils;

public class ShortURLCodeLang3 implements ShortURLCode {
    @Override
    public String create(final Integer minLength, final Integer maxLength) {
        return RandomStringUtils.randomAlphanumeric(minLength, maxLength);
    }
}
