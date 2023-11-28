package com.example.roomoccupancymanager.domain.resolver;

import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.RoomOccupancyCalculatorType;

import java.util.Collection;

public interface IRoomOccupancyCalculatorTypeResolver {

    RoomOccupancyCalculatorType resolve(Double bound, Collection<Guest> guests, int freeEconomyRooms, int freePremiumRooms);
}
