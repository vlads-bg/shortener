package com.shortener.challenge.application.shorturl.delete;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.shortener.challenge.application.UseCaseTest;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


public class DeleteShortURLUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultDeleteShortURLUseCase useCase;

    @Mock
    private ShortURLGateway shortURLGateway;

    @Mock
    private ShortURLCodeLang3 shortURLCodeLang3;

    @Override
    protected List<Object> getMocks() {
        return Arrays.asList(shortURLGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteShortURL_shouldBeOK() {
        when(shortURLCodeLang3.create(any(), any()))
            .thenReturn("ABC123");

        final ShortURL aShortURL = ShortURL.newShortURL(
            "www.google.com",
            90,
            shortURLCodeLang3,
            null
        );
        final UUID expectedId = aShortURL.getId();

        doNothing()
            .when(shortURLGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId));

        Mockito.verify(shortURLGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteCategory_shouldBeOK() {
        final UUID expectedId = UUID.randomUUID();

        doNothing()
            .when(shortURLGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId));

        Mockito.verify(shortURLGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        when(shortURLCodeLang3.create(any(), any()))
            .thenReturn("ABC123");

        final ShortURL aShortURL = ShortURL.newShortURL(
            "www.google.com",
            90,
            shortURLCodeLang3,
            null
        );

        final UUID expectedId = aShortURL.getId();

        doThrow(new IllegalStateException("Gateway error"))
            .when(shortURLGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId));

        Mockito.verify(shortURLGateway, times(1)).deleteById(eq(expectedId));
    }
}
