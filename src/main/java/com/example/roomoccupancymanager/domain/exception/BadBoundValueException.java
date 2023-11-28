package com.example.roomoccupancymanager.domain.exception;

public class BadBoundValueException extends RoomOccupancyException {

    private final String bound;

    public BadBoundValueException(String bound) {
        super(String.format("Bad bound value: %s", bound));
        this.bound = bound;
    }

    public String getBound() {
        return bound;
    }
}
