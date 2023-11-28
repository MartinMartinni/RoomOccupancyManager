package com.example.roomoccupancymanager.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRoomOccupancyResponse {

    private int economyUsage;
    private BigDecimal economyTotalPrice;
    private int premiumUsage;
    private BigDecimal premiumTotalPrice;
}
