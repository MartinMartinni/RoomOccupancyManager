package com.example.roomoccupancymanager.domain.factory;

import com.example.roomoccupancymanager.domain.model.valueobject.RoomOccupancyCalculatorType;
import com.example.roomoccupancymanager.domain.calculationstrategy.IRoomOccupancyCalculator;

public interface IRoomOccupancyCalculatorFactory {

    IRoomOccupancyCalculator create(RoomOccupancyCalculatorType type);
}
