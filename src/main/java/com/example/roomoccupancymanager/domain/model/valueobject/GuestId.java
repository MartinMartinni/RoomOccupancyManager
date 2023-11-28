package com.example.roomoccupancymanager.domain.model.valueobject;

import java.util.UUID;

public class GuestId extends BaseId<UUID> {
    public GuestId(UUID value) {
        super(value);
    }
}
