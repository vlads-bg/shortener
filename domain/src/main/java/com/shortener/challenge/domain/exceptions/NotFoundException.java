package com.shortener.challenge.domain.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
        final Class<?> aClass,
        final UUID id
    ) {
        final String anError = aClass.getSimpleName() + " with ID " + id.toString() + " was not found";
        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(
        final Class<?> aClass,
        final String code
    ) {
        final String anError = aClass.getSimpleName() + " with code " + code + " was not found";
        return new NotFoundException(anError, Collections.emptyList());
    }
}
