package com.example.roomoccupancymanager.domain.utils;

import com.example.roomoccupancymanager.domain.exception.BadBoundPriceValueException;
import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GuestsCategorizationUtilTest {

    @Test
    void givenBoundPriceAndGuests_whenCategorizationPerform_thenReturnGuestsCategorization() {

        //given
        Double boundPrice = 100d;
        final List<Guest> guests = Arrays.asList(
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

        //when
        final GuestsCategorization categorization = GuestsCategorizationUtil.categorize(boundPrice, guests);

        //then
        final List<Guest> guestsEconomy = categorization.guestsEconomy();
        final List<Guest> guestsPremium = categorization.guestsPremium();

        assertEquals(4, guestsEconomy.size());
        assertEquals(new Money(BigDecimal.valueOf(23)), guestsEconomy.get(0).getMoney());
        assertEquals(6, guestsPremium.size());
        assertEquals(new Money(BigDecimal.valueOf(155)), guestsPremium.get(0).getMoney());
    }

    @Test
    void givenBoundPriceNullAndGuests_whenCategorizationPerform_thenThrowBadBoundValueException() {
        //given
        Double boundPrice = null;
        final List<Guest> guests = Arrays.asList(
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(23))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(45))));

        //when and then
        assertThrows(BadBoundPriceValueException.class,
                () -> GuestsCategorizationUtil.categorize(boundPrice, guests));
    }
}