package com.example.roomoccupancymanager.interfaces.controller;

import com.example.roomoccupancymanager.domain.factory.IRoomOccupancyCalculatorFactory;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;
import com.example.roomoccupancymanager.domain.ports.input.IRoomOccupancyCommandHandler;
import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import com.example.roomoccupancymanager.domain.resolver.IRoomOccupancyCalculatorTypeResolver;
import com.example.roomoccupancymanager.domain.service.IRoomOccupancyService;
import com.example.roomoccupancymanager.interfaces.model.CalculationRoomOccupancyRequest;
import com.example.roomoccupancymanager.interfaces.model.CalculationRoomOccupancyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "initialize-guests=false")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomOccupancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IRoomOccupancyCalculatorTypeResolver calculatorTypeResolver;

    @Autowired
    private IRoomOccupancyCalculatorFactory calculatorFactory;

    @Autowired
    private IRoomOccupancyService roomsOccupancyService;

    @Autowired
    private IRoomOccupancyCommandHandler roomOccupancyCommandHandler;

    @Autowired
    private IGuestRepository guestRepository;

    @BeforeAll
    public void beforeAll() {
        guestRepository.saveAll(Arrays.asList(
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(23))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(45))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(155))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(374))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(22))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(99.99))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(100))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(101))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(115))),
                new Guest(new GuestId(UUID.randomUUID()), new Money(BigDecimal.valueOf(209)))));
    }

    @Test
    void given3EconomyAnd3PremiumRooms_whenReservationPerform_thenReturn3EconomyAnd3PremiumOccupiedRooms() throws Exception {
        CalculationRoomOccupancyRequest request = new CalculationRoomOccupancyRequest();
        request.setFreeEconomyRooms(3);
        request.setFreePremiumRooms(3);

        ResultActions result = mockMvc.perform(post("/room-occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        CalculationRoomOccupancyResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), CalculationRoomOccupancyResponse.class);

        assertEquals(3, response.getEconomyUsage());
        assertEquals(BigDecimal.valueOf(167.99), response.getEconomyTotalPrice());
        assertEquals(3, response.getPremiumUsage());
        assertEquals(BigDecimal.valueOf(738), response.getPremiumTotalPrice());
    }

    @Test
    void given5EconomyAnd7PremiumRooms_whenReservationPerform_thenReturn4EconomyAnd6PremiumOccupiedRooms() throws Exception {
        CalculationRoomOccupancyRequest request = new CalculationRoomOccupancyRequest();
        request.setFreeEconomyRooms(5);
        request.setFreePremiumRooms(7);

        ResultActions result = mockMvc.perform(post("/room-occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        CalculationRoomOccupancyResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), CalculationRoomOccupancyResponse.class);

        assertEquals(4, response.getEconomyUsage());
        assertEquals(BigDecimal.valueOf(189.99), response.getEconomyTotalPrice());
        assertEquals(6, response.getPremiumUsage());
        assertEquals(BigDecimal.valueOf(1054), response.getPremiumTotalPrice());
    }

    @Test
    void given7EconomyAnd2PremiumRooms_whenReservationPerform_thenReturn4EconomyAnd2PremiumOccupiedRooms() throws Exception {
        CalculationRoomOccupancyRequest request = new CalculationRoomOccupancyRequest();
        request.setFreeEconomyRooms(7);
        request.setFreePremiumRooms(2);

        ResultActions result = mockMvc.perform(post("/room-occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        CalculationRoomOccupancyResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), CalculationRoomOccupancyResponse.class);

        assertEquals(4, response.getEconomyUsage());
        assertEquals(BigDecimal.valueOf(189.99), response.getEconomyTotalPrice());
        assertEquals(2, response.getPremiumUsage());
        assertEquals(BigDecimal.valueOf(583), response.getPremiumTotalPrice());
    }

    @Test
    void given1EconomyAnd7PremiumRooms_whenReservationPerform_thenReturn1EconomyAnd7PremiumOccupiedRooms() throws Exception {
        CalculationRoomOccupancyRequest request = new CalculationRoomOccupancyRequest();
        request.setFreeEconomyRooms(1);
        request.setFreePremiumRooms(7);

        ResultActions result = mockMvc.perform(post("/room-occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        CalculationRoomOccupancyResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), CalculationRoomOccupancyResponse.class);

        assertEquals(1, response.getEconomyUsage());
        assertEquals(BigDecimal.valueOf(45), response.getEconomyTotalPrice());
        assertEquals(7, response.getPremiumUsage());
        assertEquals(BigDecimal.valueOf(1153.99), response.getPremiumTotalPrice());
    }
}