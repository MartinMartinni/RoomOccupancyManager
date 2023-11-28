package com.example.roomoccupancymanager.domain.calculationstrategy;

import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;
import com.example.roomoccupancymanager.domain.utils.GuestComparator;
import com.example.roomoccupancymanager.domain.utils.GuestsCategorizationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator implements IRoomOccupancyCalculator {
    @Override
    public RoomOccupancy calculate(double bound, Collection<Guest> guests, int freeEconomyRooms, int freePremiumRooms) {
        final GuestsCategorization categorization = GuestsCategorizationUtil.categorize(bound, guests);
        final List<Guest> guestsEconomy = categorization.guestsEconomy();
        final List<Guest> guestsPremium = categorization.guestsPremium();

        final List<Guest> guestsEconomySorted = new ArrayList<>(guestsEconomy.stream().sorted(GuestComparator.guestByMoneyRevertedComparator()).toList());

        final List<Guest> economyToPremium = guestsEconomySorted.subList(0, freePremiumRooms - guestsPremium.size());
        guestsPremium.addAll(economyToPremium);
        guestsEconomySorted.removeAll(economyToPremium);
        final Money premiumTotalPrice = guestsPremium.stream().map(Guest::getMoney).reduce(Money::add).orElse(Money.ZERO);

        final Money economyTotalPrice = guestsEconomySorted.subList(0, freeEconomyRooms).stream().map(Guest::getMoney).reduce(Money::add).orElse(Money.ZERO);
        return new RoomOccupancy(freeEconomyRooms, economyTotalPrice, freePremiumRooms, premiumTotalPrice);
    }
}
