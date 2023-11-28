package com.example.roomoccupancymanager.domain.utils;

import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.exception.BadBoundPriceValueException;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuestsCategorizationUtil {

    private GuestsCategorizationUtil() {}

    public static GuestsCategorization categorize(Double boundPrice, Collection<Guest> guests) {
        if (boundPrice == null || boundPrice < 0)
            throw new BadBoundPriceValueException(String.valueOf(boundPrice));

        List<Guest> guestsEconomy = new ArrayList<>();
        List<Guest> guestsPremium = new ArrayList<>();

        for (Guest guest : guests) {
            if (new Money(BigDecimal.valueOf(boundPrice)).isGreaterThan(guest.getMoney())) {
                guestsEconomy.add(guest);
                continue;
            }

            guestsPremium.add(guest);
        }

        return new GuestsCategorization(guestsEconomy, guestsPremium);
    }
}
