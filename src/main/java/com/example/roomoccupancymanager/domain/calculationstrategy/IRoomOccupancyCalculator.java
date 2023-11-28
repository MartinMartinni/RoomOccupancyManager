package com.example.roomoccupancymanager.domain.calculationstrategy;

import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.entity.Guest;

import java.util.Collection;

public interface IRoomOccupancyCalculator {

    RoomOccupancy calculate(double bound, Collection<Guest> guests, int freeEconomyRooms, int freePremiumRooms);
}
