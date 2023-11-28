package com.example.roomoccupancymanager.domain.service;

import com.example.roomoccupancymanager.domain.exception.BadBoundPriceValueException;
import com.example.roomoccupancymanager.domain.factory.IRoomOccupancyCalculatorFactory;
import com.example.roomoccupancymanager.domain.model.dto.CalculationRoomOccupancyCommand;
import com.example.roomoccupancymanager.domain.model.dto.RoomOccupancy;
import com.example.roomoccupancymanager.domain.model.entity.Guest;
import com.example.roomoccupancymanager.domain.model.valueobject.GuestId;
import com.example.roomoccupancymanager.domain.model.valueobject.Money;
import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import com.example.roomoccupancymanager.domain.resolver.IRoomOccupancyCalculatorTypeResolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "initialize-guests=false")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomOccupancyServiceImplTest {

    @Autowired
    private IRoomOccupancyCalculatorTypeResolver calculatorTypeResolver;

    @Autowired
    private IRoomOccupancyCalculatorFactory calculatorFactory;

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
    void givenBoundPrice100AndQuestsPricesAnd3EconomyAnd3PremiumRooms_whenReservationPerform_thenReturn3EconomyAnd3PremiumOccupiedRooms() {
        // given
        Double boundPrice = 100d;

        int freeEconomyRooms = 3;
        int freePremiumRooms = 3;

        // when
        final RoomOccupancy roomOccupancy = new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository).calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms));

        // then
        assertEquals(3, roomOccupancy.economyUsage());
        assertEquals(new Money(BigDecimal.valueOf(167.99)), roomOccupancy.economyTotalPrice());
        assertEquals(3, roomOccupancy.premiumUsage());
        assertEquals(new Money(BigDecimal.valueOf(738)), roomOccupancy.premiumTotalPrice());
    }

    @Test
    void givenBoundPrice100AndQuestsPricesAnd5EconomyAnd7PremiumRooms_whenReservationPerform_thenReturn4EconomyAnd6PremiumOccupiedRooms() {
        // given
        Double boundPrice = 100d;

        int freeEconomyRooms = 5;
        int freePremiumRooms = 7;

        // when
        final RoomOccupancy roomOccupancy = new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository).calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms));

        // then
        assertEquals(4, roomOccupancy.economyUsage());
        assertEquals(new Money(BigDecimal.valueOf(189.99)), roomOccupancy.economyTotalPrice());
        assertEquals(6, roomOccupancy.premiumUsage());
        assertEquals(new Money(BigDecimal.valueOf(1054)), roomOccupancy.premiumTotalPrice());
    }

    @Test
    void givenBoundPrice100AndQuestsPricesAnd7EconomyAnd2PremiumRooms_whenReservationPerform_thenReturn4EconomyAnd2PremiumOccupiedRooms() {
        // given
        Double boundPrice = 100d;

        int freeEconomyRooms = 7;
        int freePremiumRooms = 2;

        // when
        final RoomOccupancy roomOccupancy = new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository).calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms));

        // then
        assertEquals(4, roomOccupancy.economyUsage());
        assertEquals(new Money(BigDecimal.valueOf(189.99)), roomOccupancy.economyTotalPrice());
        assertEquals(2, roomOccupancy.premiumUsage());
        assertEquals(new Money(BigDecimal.valueOf(583)), roomOccupancy.premiumTotalPrice());
    }

    @Test
    void givenBoundPrice100AndQuestsPricesAnd1EconomyAnd7PremiumRooms_whenReservationPerform_thenReturn1EconomyAnd7PremiumOccupiedRooms() {
        // given
        Double boundPrice = 100d;

        int freeEconomyRooms = 1;
        int freePremiumRooms = 7;

        // when
        final RoomOccupancy roomOccupancy = new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository).calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms));

        // then
        assertEquals(1, roomOccupancy.economyUsage());
        assertEquals(new Money(BigDecimal.valueOf(45)), roomOccupancy.economyTotalPrice());
        assertEquals(7, roomOccupancy.premiumUsage());
        assertEquals(new Money(BigDecimal.valueOf(1153.99)), roomOccupancy.premiumTotalPrice());
    }

    @Test
    void givenBoundPrice23AndQuestsPricesAnd3EconomyAnd3PremiumRooms_whenReservationPerform_thenReturn1EconomyAnd3PremiumOccupiedRooms() {
        // given
        Double boundPrice = 23d;

        int freeEconomyRooms = 3;
        int freePremiumRooms = 3;

        // when
        final RoomOccupancy roomOccupancy = new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository).calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms));

        // then
        assertEquals(1, roomOccupancy.economyUsage());
        assertEquals(new Money(BigDecimal.valueOf(22)), roomOccupancy.economyTotalPrice());
        assertEquals(3, roomOccupancy.premiumUsage());
        assertEquals(new Money(BigDecimal.valueOf(738)), roomOccupancy.premiumTotalPrice());
    }

    @Test
    void givenBoundPriceNullAndQuestsPricesAnd3EconomyAnd3PremiumRooms_whenReservationPerform_thenThrowBadBoundValueException() {
        // given
        Double boundPrice = null;

        int freeEconomyRooms = 3;
        int freePremiumRooms = 3;

        // when and then
        assertThrows(BadBoundPriceValueException.class,
                () -> new RoomOccupancyServiceImpl(boundPrice, calculatorTypeResolver, calculatorFactory, guestRepository)
                        .calculateOccupancy(new CalculationRoomOccupancyCommand(freeEconomyRooms, freePremiumRooms)));
    }
}