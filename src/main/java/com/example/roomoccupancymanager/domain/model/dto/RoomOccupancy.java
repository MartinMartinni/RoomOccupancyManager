package com.example.roomoccupancymanager.domain.model.dto;

import com.example.roomoccupancymanager.domain.model.valueobject.Money;

public record RoomOccupancy(int economyUsage, Money economyTotalPrice, int premiumUsage, Money premiumTotalPrice) {}
