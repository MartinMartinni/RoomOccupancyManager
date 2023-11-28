package com.example.roomoccupancymanager.domain.ports.output;

import com.example.roomoccupancymanager.domain.model.entity.Guest;

import java.util.Collection;
import java.util.List;

public interface IGuestRepository {

    void saveAll(List<Guest> rooms);
    Collection<Guest> findAll();
}
