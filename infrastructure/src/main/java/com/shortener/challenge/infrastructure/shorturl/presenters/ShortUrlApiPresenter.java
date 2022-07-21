package com.shortener.challenge.infrastructure.shorturl.presenters;

import com.shortener.challenge.application.shorturl.create.CreateShortURLOutput;
import com.shortener.challenge.infrastructure.shorturl.ShorUrlResponse;

public interface ShortUrlApiPresenter {
    static ShorUrlResponse present(final CreateShortURLOutput output) {
        return new ShorUrlResponse(
//            output.
        );
    }
}
