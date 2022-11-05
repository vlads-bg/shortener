package com.shortener.challenge.infrastructure.configuration.usecases;

import com.shortener.challenge.application.shorturl.create.CreateShortURLUseCase;
import com.shortener.challenge.application.shorturl.create.DefaultCreateShortURLUseCase;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCode;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ShortURLUseCaseConfig {
    private final ShortURLGateway shortURLGateway;
    private final ShortURLCode shortURLCode;

    @Bean
    public CreateShortURLUseCase createShortURLUseCase() {
        return new DefaultCreateShortURLUseCase(shortURLGateway, shortURLCode);
    }
}
