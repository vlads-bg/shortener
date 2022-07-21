package com.shortener.challenge.application.shorturl.create;

import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import java.util.Objects;

public class DefaultCreateShortURLUseCase extends CreateShortURLUseCase {

    private final ShortURLGateway shortURLGateway;
    private final ShortURLCode shortURLCode;

    public DefaultCreateShortURLUseCase(
        ShortURLGateway shortURLGateway,
        ShortURLCode shortURLCode
    ) {
        this.shortURLGateway = Objects.requireNonNull(shortURLGateway);
        this.shortURLCode = Objects.requireNonNull(shortURLCode);
    }

    @Override
    public CreateShortURLOutput execute(CreateShortURLCommand anIn) {
       ShortURL aShortURL = ShortURL.newShortURL(anIn.getTarget(), anIn.getSecondsInMemory(), this.shortURLCode, null);
       return CreateShortURLOutput.from(this.shortURLGateway.create(aShortURL));
    }
}
