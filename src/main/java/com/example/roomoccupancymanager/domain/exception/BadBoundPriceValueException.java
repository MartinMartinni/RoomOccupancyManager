package com.example.roomoccupancymanager.domain.exception;

public class BadBoundPriceValueException extends RoomOccupancyException {

    private final String boundPrice;

    public BadBoundPriceValueException(String bound) {
        super(String.format("Bad bound price value: %s", bound));
        this.boundPrice = bound;
    }

    public String getBoundPrice() {
        return boundPrice;
    }
}
