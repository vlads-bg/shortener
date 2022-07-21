package com.shortener.challenge.application.shorturl.create;

import com.shortener.challenge.domain.shortURL.ShortURL;

public class CreateShortURLOutput {

    private String target;
    private String code;

    CreateShortURLOutput(
        String target,
        String code
    ) {
        this.target = target;
        this.code = code;
    }

    public static CreateShortURLOutput from(final ShortURL aShortURL) {
        return new CreateShortURLOutput(
            aShortURL.getTarget(),
            aShortURL.getCode()
        );
    }

    public String getTarget() {
        return target;
    }

    public String getCode() {
        return code;
    }
}
