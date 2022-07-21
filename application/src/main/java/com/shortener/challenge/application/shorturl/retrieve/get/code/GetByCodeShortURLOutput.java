package com.shortener.challenge.application.shorturl.retrieve.get.code;

import com.shortener.challenge.domain.shortURL.ShortURL;

public class GetByCodeShortURLOutput {

    private final String target;
    private final Boolean isValid;

    GetByCodeShortURLOutput(
        final String target,
        final Boolean isValid
    ) {
        this.target = target;
        this.isValid = isValid;
    }

    public String getTarget() {
        return target;
    }

    public Boolean isValid() {
        return isValid;
    }

    public static GetByCodeShortURLOutput from(final ShortURL aShortURL) {
        return new GetByCodeShortURLOutput(
            aShortURL.getTarget(),
            aShortURL.isValid());
    }
}
