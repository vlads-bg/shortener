package com.shortener.challenge.infrastructure.configuration.usecases;

import com.shortener.challenge.application.shorturl.create.CreateShortURLUseCase;
import com.shortener.challenge.application.shorturl.create.DefaultCreateShortURLUseCase;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortURLUseCaseConfig {
    private final ShortURLGateway shortURLGateway;
    private final ShortURLCode shortURLCode;

    public ShortURLUseCaseConfig(ShortURLGateway shortURLGateway, ShortURLCode shortURLCode) {
        this.shortURLGateway = shortURLGateway;
        this.shortURLCode = shortURLCode;
    }

    @Bean
    public CreateShortURLUseCase createShortURLUseCase() {
        return new DefaultCreateShortURLUseCase(shortURLGateway, shortURLCode);
    }
}
