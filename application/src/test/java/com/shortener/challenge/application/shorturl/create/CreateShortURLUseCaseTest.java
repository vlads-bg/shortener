package com.shortener.challenge.application.shorturl.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.shortener.challenge.application.UseCaseTest;
import com.shortener.challenge.domain.shortURL.ShortURLGateway;
import com.shortener.challenge.domain.shortURL.code.ShortURLCodeLang3;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CreateShortURLUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateShortURLUseCase useCase;

    @Mock
    private ShortURLGateway shortURLGateway;

    @Mock
    private ShortURLCodeLang3 shortURLCodeLang3;

    @Override
    protected List<Object> getMocks() {
        return Arrays.asList(shortURLGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateShortURL_shouldReturnId() {
        final String expectedTarget = "www.google.com";
        final String expectedCode = "ABC123";
        final Integer expectedSecondsInMemory = 90;
        final Boolean expectedIsValid = true;

        final CreateShortURLCommand aCommand =
            CreateShortURLCommand.with(expectedTarget, expectedSecondsInMemory);

        when(shortURLGateway.create(any()))
            .thenAnswer(returnsFirstArg());

        when(shortURLCodeLang3.create(any(), any()))
            .thenReturn(expectedCode);

        final CreateShortURLOutput actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.getTarget());
        Assertions.assertNotNull(actualOutput.getCode());

        Mockito.verify(shortURLGateway, times(1)).create(argThat(aShortURL ->
            Objects.equals(expectedTarget, aShortURL.getTarget())
                && Objects.equals(expectedIsValid, aShortURL.isValid())
                && Objects.equals(expectedCode, aShortURL.getCode())
                && Objects.equals(aShortURL.getCreatedAt().plusSeconds(expectedSecondsInMemory), aShortURL.getValidity())
                && Objects.nonNull(aShortURL.getId())
                && Objects.nonNull(aShortURL.getCode())
                && Objects.nonNull(aShortURL.getCreatedAt())
        ));
    }

    @Test
    public void givenAInvalidTarget_whenCallsCreateShortURL_thenShouldReturnException() {
        final Integer expectedSecondsInMemory = 90;
        final String expectedErrorMessage = "'target' should not be null";

        final CreateShortURLCommand aCommand =
            CreateShortURLCommand.with(null, expectedSecondsInMemory);

        when(shortURLCodeLang3.create(any(), any()))
            .thenReturn("ABC123");

        final NullPointerException actualException = Assertions.assertThrows(NullPointerException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(shortURLGateway, times(0)).create(any());
    }

    @Test
    public void givenAInvalidCode_whenCallsCreateShortURL_thenShouldReturnException() {
        final String expectedTarget = "www.google.com";
        final Integer expectedSecondsInMemory = 90;
        final String expectedErrorMessage = "'code' should not be null";

        final CreateShortURLCommand aCommand =
            CreateShortURLCommand.with(expectedTarget, expectedSecondsInMemory);

        final NullPointerException actualException = Assertions.assertThrows(NullPointerException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(shortURLGateway, times(0)).create(any());
    }
}
