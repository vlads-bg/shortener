package com.shortener.challenge.infrastructure.api.controllers;

import com.shortener.challenge.application.shorturl.create.CreateShortURLCommand;
import com.shortener.challenge.application.shorturl.create.CreateShortURLUseCase;
import com.shortener.challenge.application.shorturl.retrieve.get.code.GetByCodeShortURLOutput;
import com.shortener.challenge.application.shorturl.retrieve.get.code.GetByCodeShortURLUseCase;
import com.shortener.challenge.infrastructure.api.ShortUrlAPI;
import com.shortener.challenge.infrastructure.shorturl.ShortURLRequest;
import com.shortener.challenge.infrastructure.shorturl.models.ShortUrlResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.function.Function;

@RestController
@AllArgsConstructor
public class ShortUrlController implements ShortUrlAPI {
    private final CreateShortURLUseCase createShortURLUseCase;
    private final GetByCodeShortURLUseCase getByCodeShortURLUseCase;

    @Override
    public ResponseEntity<?> shortener(String url, ShortURLRequest input) {
        final var aCommand = CreateShortURLCommand.with("input.url", null);
        final var output =  this.createShortURLUseCase.execute(aCommand);

        return ResponseEntity
                .created(URI.create("/shortener/" + output.target()))
                .body(ShortUrlResponse.with(output.code(), output.target()));
    }

    @Override
    public void redirect(String code, HttpServletRequest request, HttpServletResponse response) {
        final var output = this.getByCodeShortURLUseCase.execute(code);
        if (output.isValid()) {
            response.setHeader("Location", output.target());
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
            // TODO: exception url não é mais valida
        }
    }

    @Override
    public ResponseEntity<?> status(String code) {
        // TODO: retornar estatisticas
        return null;
    }
}
