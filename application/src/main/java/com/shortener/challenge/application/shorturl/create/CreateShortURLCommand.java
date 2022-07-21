package com.shortener.challenge.application.shorturl.create;

import java.time.Instant;

public class CreateShortURLCommand {
    private String target;
    private Integer secondsInMemory;

    public String getTarget() {
        return target;
    }

    public Integer getSecondsInMemory() {
        return secondsInMemory;
    }

    CreateShortURLCommand (
        final String target,
        final Integer secondsInMemory

    ) {
        this.target = target;
        this.secondsInMemory = secondsInMemory;
    }

    public static CreateShortURLCommand with(
        final String target,
        final Integer secondsInMemory
    ) {
        return new CreateShortURLCommand(target, secondsInMemory);
    }
}
