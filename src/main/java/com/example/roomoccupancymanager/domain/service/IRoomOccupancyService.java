package com.example.roomoccupancymanager.domain.service;

import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;

public interface IRoomOccupancyService {

    RoomOccupancy calculateOccupancy(CalculationRoomOccupancyCommand command);
}
