package com.shortener.challenge.application.shorturl.create;

import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DefaultCreateShortURLUseCase extends CreateShortURLUseCase {
    @NonNull
    private final ShortURLGateway shortURLGateway;
    @NonNull
    private final ShortURLCode shortURLCode;

    @Override
    public CreateShortURLOutput execute(CreateShortURLCommand anIn) {
        ShortURL aShortURL = ShortURL.newShortURL(anIn.getTarget(), anIn.getSecondsInMemory(), this.shortURLCode);
        return CreateShortURLOutput.from(this.shortURLGateway.create(aShortURL));
    }
}
