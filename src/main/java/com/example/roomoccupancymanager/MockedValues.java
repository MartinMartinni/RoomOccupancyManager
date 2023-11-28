package com.example.roomoccupancymanager;

import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MockedValues {

    private MockedValues() {}

    public static final List<Guest> GUESTS_REPRESENTED_BY_PRICES = Arrays.asList(
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(23))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(45))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(155))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(374))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(22))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(99.99))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(100))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(101))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(115))),
            new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(209))));
}