package com.example.roomoccupancymanager.domain.service;

import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import com.example.roomoccupancymanager.domain.resolver.IRoomOccupancyCalculatorTypeResolver;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.valueobject.RoomOccupancyCalculatorType;
import com.example.roomoccupancymanager.domain.calculationstrategy.IRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.factory.IRoomOccupancyCalculatorFactory;

import java.util.Collection;

public class RoomOccupancyServiceImpl implements IRoomOccupancyService {

    private final Double bound;
    private final IRoomOccupancyCalculatorTypeResolver calculatorTypeResolver;
    private final IRoomOccupancyCalculatorFactory calculatorFactory;

    private final IGuestRepository guestRepository;

    public RoomOccupancyServiceImpl(Double bound,
                                    IRoomOccupancyCalculatorTypeResolver calculatorTypeResolver,
                                    IRoomOccupancyCalculatorFactory calculatorFactory,
                                    IGuestRepository guestRepository) {
        this.bound = bound;
        this.calculatorTypeResolver = calculatorTypeResolver;
        this.calculatorFactory = calculatorFactory;
        this.guestRepository = guestRepository;
    }

    @Override
    public RoomOccupancy calculateOccupancy(CalculationRoomOccupancyCommand command) {
        final Collection<Guest> guests = guestRepository.findAll();
        final RoomOccupancyCalculatorType roomOccupancyCalculatorType = calculatorTypeResolver.resolve(bound,
                guests, command.freeEconomyRooms(), command.freePremiumRooms());
        final IRoomOccupancyCalculator calculator = calculatorFactory.create(roomOccupancyCalculatorType);
        return calculator.calculate(bound, guests, command.freeEconomyRooms(), command.freePremiumRooms());
    }
}
