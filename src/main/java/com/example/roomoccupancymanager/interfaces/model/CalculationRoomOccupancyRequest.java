package com.example.roomoccupancymanager.interfaces.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CalculationRoomOccupancyRequest {

    @Min(value = 0, message = "Free economy rooms must not be less than 0")
    @NotNull(message = "Free economy rooms must be provided")
    private Integer freeEconomyRooms;

    @Min(value = 0, message = "Free premium rooms must not be less than 0")
    @NotNull(message = "Free premium rooms must be provided")
    private Integer freePremiumRooms;
}
