package com.shortener.challenge.application.shorturl.retrieve.get.id;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.shortener.challenge.application.UseCaseTest;
import com.shortener.challenge.domain.exceptions.NotFoundException;
import com.shortener.challenge.domain.shortURL.ShortURL;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GetByIdShortURLUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultGetByIdShortURLUseCase useCase;

    @Mock
    private ShortURLGateway shortURLGateway;

    @Mock
    private ShortURLCodeLang3 shortURLCodeLang3;

    @Override
    protected List<Object> getMocks() {
        return Arrays.asList(shortURLGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetShortURL_shouldReturnShortURL() {
        final String expectedTarget = "www.google.com";
        final String expectedCode = "ABC123";

        when(shortURLCodeLang3.create(any(), any()))
            .thenReturn(expectedCode);

        final ShortURL aShortURL = ShortURL.newShortURL(
            expectedTarget,
            90,
            shortURLCodeLang3,
            null
        );
        final UUID expectedId = aShortURL.getId();

        when(shortURLGateway.findById(eq(expectedId)))
            .thenReturn(Optional.of(aShortURL.clone()));

        final GetByIdShortURLOutput actualShortURL = useCase.execute(expectedId);

        Assertions.assertEquals(expectedId, actualShortURL.getId());
        Assertions.assertEquals(expectedCode, actualShortURL.getCode());
        Assertions.assertEquals(aShortURL.getCreatedAt(), actualShortURL.getCreatedAt());
        Assertions.assertEquals(aShortURL.getValidity(), actualShortURL.getValidity());
    }

    @Test
    public void givenAInvalidId_whenCallsGetShortURL_shouldReturnNotFound() {
        final UUID expectedId = UUID.randomUUID();
        final String expectedErrorMessage = "ShortURL with ID " + expectedId + " was not found";

        when(shortURLGateway.findById(eq(expectedId)))
            .thenReturn(Optional.empty());

        final Throwable actualException = Assertions.assertThrows(
            NotFoundException.class,
            () -> useCase.execute(expectedId)
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final String expectedErrorMessage = "Gateway error";
        final UUID expectedId = UUID.randomUUID();

        when(shortURLGateway.findById(eq(expectedId)))
            .thenThrow(new IllegalStateException(expectedErrorMessage));

        final Throwable actualException = Assertions.assertThrows(
            IllegalStateException.class,
            () -> useCase.execute(expectedId)
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
