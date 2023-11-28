package com.example.roomoccupancymanager.infrastructure;

import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class GuestRepositoryImpl implements IGuestRepository {

    private final Map<GuestId, Guest> guestsInDb = new HashMap<>();

    @Override
    public void saveAll(List<Guest> guestsToSave) {
        guestsToSave.forEach(guest -> guestsInDb.put(new GuestId(UUID.randomUUID()), guest));
    }

    @Override
    public Collection<Guest> findAll() {
        return guestsInDb.values();
    }
}
