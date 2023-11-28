package com.example.roomoccupancymanager;

import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuestDataLoader implements CommandLineRunner {

    @Value("${initialize-guests}")
    private Boolean initializeGuests;

    private final IGuestRepository guestRepository;

    public static void main(String[] args) {
        SpringApplication.run(RoomOccupancyManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (initializeGuests != null && initializeGuests) {
            guestRepository.saveAll(MockedValues.GUESTS_REPRESENTED_BY_PRICES);
        }
    }
}