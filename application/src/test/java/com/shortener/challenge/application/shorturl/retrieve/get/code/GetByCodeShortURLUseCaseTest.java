package com.shortener.challenge.application.shorturl.retrieve.get.code;

import com.shortener.challenge.application.UseCaseTest;
import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GetByCodeShortURLUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultGetByCodeShortURLUseCase useCase;

    @Mock
    private ShortURLGateway shortURLGateway;

    @Mock
    private ShortURLCodeLang3 shortURLCodeLang3;

    @Override
    protected List<Object> getMocks() {
        return List.of(shortURLGateway);
    }

    @Test
    public void givenAValidCode_whenCallsGetShortURL_shouldReturnShortURL() {
        final String expectedTarget = "www.google.com";
        final String expectedCode = "ABC123";

        when(shortURLCodeLang3.create(any(), any()))
                .thenReturn(expectedCode);

        final ShortURL aShortURL = ShortURL.newShortURL(expectedTarget, 90, shortURLCodeLang3);

        when(shortURLGateway.findByCode(eq(expectedCode)))
                .thenReturn(Optional.of(aShortURL.clone()));

        final GetByCodeShortURLOutput actualShortURL = useCase.execute(expectedCode);

        Assertions.assertEquals(expectedTarget, actualShortURL.target());
        Assertions.assertTrue(actualShortURL.isValid());
    }

    @Test
    public void givenAInvalidId_whenCallsGetShortURL_shouldReturnNotFound() {
        final String expectedCode = "ABC123";
        final String expectedErrorMessage = "ShortURL with code " + expectedCode + " was not found";

        when(shortURLGateway.findByCode(eq(expectedCode)))
                .thenReturn(Optional.empty());

        final Throwable actualException = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(expectedCode)
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final String expectedErrorMessage = "Gateway error";
        final String expectedCode = "ABC123";

        when(shortURLGateway.findByCode(eq(expectedCode)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final Throwable actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedCode)
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
