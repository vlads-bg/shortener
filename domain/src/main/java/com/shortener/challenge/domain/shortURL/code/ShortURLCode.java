package com.shortener.challenge.domain.shortURL.code;

public interface ShortURLCode {
    String create(Integer minLength, Integer maxLength);

}
