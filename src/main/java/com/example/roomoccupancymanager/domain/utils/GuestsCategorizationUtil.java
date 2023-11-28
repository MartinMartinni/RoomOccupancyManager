package com.example.roomoccupancymanager.domain.utils;

import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.exception.BadBoundValueException;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuestsCategorizationUtil {

    private GuestsCategorizationUtil() {}

    public static GuestsCategorization categorize(Double bound, Collection<Guest> guests) {
        if (bound == null || bound < 0)
            throw new BadBoundValueException(String.valueOf(bound));

        List<Guest> guestsEconomy = new ArrayList<>();
        List<Guest> guestsPremium = new ArrayList<>();

        for (Guest guest : guests) {
            if (new Money(BigDecimal.valueOf(bound)).isGreaterThan(guest.getMoney())) {
                guestsEconomy.add(guest);
                continue;
            }

            guestsPremium.add(guest);
        }

        return new GuestsCategorization(guestsEconomy, guestsPremium);
    }
}
