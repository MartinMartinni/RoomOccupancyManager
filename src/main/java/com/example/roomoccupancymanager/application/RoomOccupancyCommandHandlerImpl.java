package com.example.roomoccupancymanager.application;

import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.ports.input.IRoomOccupancyCommandHandler;
import com.example.roomoccupancymanager.domain.service.IRoomOccupancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomOccupancyCommandHandlerImpl implements IRoomOccupancyCommandHandler {

    private final IRoomOccupancyService roomOccupancyService;

    @Override
    public RoomOccupancy calculateOccupancy(CalculationRoomOccupancyCommand command) {
        return roomOccupancyService.calculateOccupancy(command);
    }
}
