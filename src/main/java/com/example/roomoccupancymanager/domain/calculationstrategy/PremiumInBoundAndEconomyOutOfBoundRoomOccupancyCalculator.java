package com.example.roomoccupancymanager.domain.calculationstrategy;

import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;
import com.example.roomoccupancymanager.domain.utils.GuestComparator;
import com.example.roomoccupancymanager.domain.utils.GuestsCategorizationUtil;

import java.util.Collection;
import java.util.List;

public class PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator implements IRoomOccupancyCalculator {
    @Override
    public RoomOccupancy calculate(double bound, Collection<Guest> guests, int freeEconomyRooms, int freePremiumRooms) {
        final GuestsCategorization categorization = GuestsCategorizationUtil.categorize(bound, guests);
        final List<Guest> guestsEconomy = categorization.guestsEconomy();
        final List<Guest> guestsPremium = categorization.guestsPremium();

        final Money economyTotalPrice = guestsEconomy.stream().map(Guest::getMoney).reduce(Money::add).orElse(Money.ZERO);
        final List<Guest> guestsPremiumSorted = guestsPremium.stream().sorted(GuestComparator.guestByMoneyRevertedComparator()).toList();

        final Money premiumTotalPrice = guestsPremiumSorted.subList(0, freePremiumRooms).stream().map(Guest::getMoney).reduce(Money::add).orElse(Money.ZERO);
        return new RoomOccupancy(guestsEconomy.size(), economyTotalPrice, freePremiumRooms, premiumTotalPrice);
    }
}
