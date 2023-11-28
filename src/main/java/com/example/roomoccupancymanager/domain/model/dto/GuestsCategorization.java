package com.example.roomoccupancymanager.domain.model.dto;

import com.example.roomoccupancymanager.domain.model.entity.Guest;

import java.util.List;

public record GuestsCategorization(List<Guest> guestsEconomy, List<Guest> guestsPremium) {
}
