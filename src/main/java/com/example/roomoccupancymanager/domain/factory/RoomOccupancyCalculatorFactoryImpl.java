package com.example.roomoccupancymanager.domain.factory;

import com.example.roomoccupancymanager.domain.exception.UnsupportedRoomOccupancyCalculatorTypeException;
import com.example.roomoccupancymanager.domain.model.valueobject.RoomOccupancyCalculatorType;
import com.example.roomoccupancymanager.domain.calculationstrategy.IRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumAndEconomyBothInBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumAndEconomyBothOutOfBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator;

public class RoomOccupancyCalculatorFactoryImpl implements IRoomOccupancyCalculatorFactory {

    private final PremiumAndEconomyBothInBoundRoomOccupancyCalculator premiumAndEconomyBothInBoundRoomOccupancyCalculator;
    private final PremiumAndEconomyBothOutOfBoundRoomOccupancyCalculator premiumAndEconomyBothOutOfBoundRoomOccupancyCalculator;
    private final PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator premiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator;
    private final PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator premiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator;

    public RoomOccupancyCalculatorFactoryImpl(PremiumAndEconomyBothInBoundRoomOccupancyCalculator premiumAndEconomyBothInBoundRoomOccupancyCalculator,
                                              PremiumAndEconomyBothOutOfBoundRoomOccupancyCalculator premiumAndEconomyBothOutOfBoundRoomOccupancyCalculator,
                                              PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator premiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator,
                                              PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator premiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator) {
        this.premiumAndEconomyBothInBoundRoomOccupancyCalculator = premiumAndEconomyBothInBoundRoomOccupancyCalculator;
        this.premiumAndEconomyBothOutOfBoundRoomOccupancyCalculator = premiumAndEconomyBothOutOfBoundRoomOccupancyCalculator;
        this.premiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator = premiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator;
        this.premiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator = premiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator;
    }

    @Override
    public IRoomOccupancyCalculator create(RoomOccupancyCalculatorType type) {
        IRoomOccupancyCalculator calculableRoomOccupancy;
        switch (type) {
            case PREMIUM_AND_ECONOMY_BOTH_IN_BOUND -> calculableRoomOccupancy = premiumAndEconomyBothInBoundRoomOccupancyCalculator;
            case PREMIUM_AND_ECONOMY_BOTH_OUT_OF_BOUND -> calculableRoomOccupancy = premiumAndEconomyBothOutOfBoundRoomOccupancyCalculator;
            case PREMIUM_IN_BOUND_AND_ECONOMY_OUT_OF_BOUND -> calculableRoomOccupancy = premiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator;
            case PREMIUM_OUT_OF_BOUND_AND_ECONOMY_IN_BOUND -> calculableRoomOccupancy = premiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator;
            default -> throw new UnsupportedRoomOccupancyCalculatorTypeException(String.valueOf(type));
        }

        return calculableRoomOccupancy;
    }
}
