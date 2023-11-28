package com.example.roomoccupancymanager.interfaces.controller;

import com.example.roomoccupancymanager.domain.ports.input.IRoomOccupancyCommandHandler;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;
import com.example.roomoccupancymanager.interfaces.model.CalculationRoomOccupancyRequest;
import com.example.roomoccupancymanager.interfaces.model.CalculationRoomOccupancyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@RestController
@Log
@RequiredArgsConstructor
@RequestMapping("room-occupancy")
public class RoomOccupancyController {

    private final IRoomOccupancyCommandHandler commandHandler;

    @PostMapping
    public ResponseEntity<CalculationRoomOccupancyResponse> calculateRoomOccupancy(@Valid @RequestBody CalculationRoomOccupancyRequest request) {
        log.log(Level.INFO, request.toString());
        final RoomOccupancy roomOccupancy = commandHandler.calculateOccupancy(new CalculationRoomOccupancyCommand(request.getFreeEconomyRooms(), request.getFreePremiumRooms()));
        return new ResponseEntity<>(new CalculationRoomOccupancyResponse(
                roomOccupancy.economyUsage(),
                roomOccupancy.economyTotalPrice().amount(),
                roomOccupancy.premiumUsage(),
                roomOccupancy.premiumTotalPrice().amount()),
                HttpStatus.CREATED);
    }
}
