package com.example.roomoccupancymanager.domain.utils;

import com.example.roomoccupancymanager.domain.model.entity.Guest;

import java.util.Comparator;

public class GuestComparator {

    public static Comparator<Guest> guestByMoneyRevertedComparator() {
        return Comparator.comparing(Guest::getMoney).reversed();
    }
}
