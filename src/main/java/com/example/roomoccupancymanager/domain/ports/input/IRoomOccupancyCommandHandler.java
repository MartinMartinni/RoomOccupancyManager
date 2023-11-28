package com.example.roomoccupancymanager.domain.ports.input;

import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;

public interface IRoomOccupancyCommandHandler {

    RoomOccupancy calculateOccupancy(CalculationRoomOccupancyCommand command);
}
