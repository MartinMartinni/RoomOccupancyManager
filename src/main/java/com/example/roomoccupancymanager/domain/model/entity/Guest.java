package com.example.roomoccupancymanager.domain.model.entity;

import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;

public class Guest extends BaseEntity<GuestId> {

    private final Money money;

    public Guest(GuestId guestId, Money money) {
        super.setId(guestId);
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }
}
