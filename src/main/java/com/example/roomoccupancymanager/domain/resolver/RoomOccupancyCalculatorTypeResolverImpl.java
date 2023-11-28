package com.example.roomoccupancymanager.domain.resolver;

import com.example.roomoccupancymanager.domain.model.dto.GuestsCategorization;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.RoomOccupancyCalculatorType;
import com.example.roomoccupancymanager.domain.utils.GuestsCategorizationUtil;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomOccupancyCalculatorTypeResolverImpl implements IRoomOccupancyCalculatorTypeResolver {

    private static final Logger logger = Logger.getLogger(RoomOccupancyCalculatorTypeResolverImpl.class.getName());

    @Override
    public RoomOccupancyCalculatorType resolve(Double boundPrice, Collection<Guest> guests, int freeEconomyRooms, int freePremiumRooms) {
        final GuestsCategorization categorization = GuestsCategorizationUtil.categorize(boundPrice, guests);

        final List<Guest> guestsEconomy = categorization.guestsEconomy();
        final List<Guest> guestsPremium = categorization.guestsPremium();

        RoomOccupancyCalculatorType type;

        if (freeEconomyRooms <= guestsEconomy.size() && freePremiumRooms <= guestsPremium.size()) {
            type = RoomOccupancyCalculatorType.PREMIUM_AND_ECONOMY_BOTH_IN_BOUND;
        } else if (freeEconomyRooms > guestsEconomy.size() && freePremiumRooms > guestsPremium.size()) {
            type = RoomOccupancyCalculatorType.PREMIUM_AND_ECONOMY_BOTH_OUT_OF_BOUND;
        } else if (freePremiumRooms <= guestsPremium.size()) {
            type = RoomOccupancyCalculatorType.PREMIUM_IN_BOUND_AND_ECONOMY_OUT_OF_BOUND;
        } else {
            type = RoomOccupancyCalculatorType.PREMIUM_OUT_OF_BOUND_AND_ECONOMY_IN_BOUND;
        }

        logger.log(Level.INFO, "RoomOccupancyCalculatorType: {0}", type);

        return type;
    }
}
