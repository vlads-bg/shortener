package com.shortener.challenge.application.shorturl.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateShortURLCommand {
    private String target;
    private Integer secondsInMemory;

    public static CreateShortURLCommand with(final String target, final Integer secondsInMemory) {
        return new CreateShortURLCommand(target, secondsInMemory);
    }
}
