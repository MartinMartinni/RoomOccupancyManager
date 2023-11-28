package com.example.roomoccupancymanager.configuration;

import com.example.roomoccupancymanager.domain.factory.RoomOccupancyCalculatorFactoryImpl;
import com.example.roomoccupancymanager.domain.ports.output.IGuestRepository;
import com.example.roomoccupancymanager.domain.service.IRoomOccupancyService;
import com.example.roomoccupancymanager.domain.service.RoomOccupancyServiceImpl;
import com.example.roomoccupancymanager.domain.factory.IRoomOccupancyCalculatorFactory;
import com.example.roomoccupancymanager.domain.resolver.IRoomOccupancyCalculatorTypeResolver;
import com.example.roomoccupancymanager.domain.resolver.RoomOccupancyCalculatorTypeResolverImpl;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumAndEconomyBothInBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumAndEconomyBothOutOfBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator;
import com.example.roomoccupancymanager.domain.calculationstrategy.PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Config {

    @Value("${bound}")
    private Double bound;

    private final IGuestRepository guestRepository;

    @Bean
    public IRoomOccupancyCalculatorFactory roomOccupancyCalculatorFactory() {
        return new RoomOccupancyCalculatorFactoryImpl(
                new PremiumAndEconomyBothInBoundRoomOccupancyCalculator(),
                new PremiumAndEconomyBothOutOfBoundRoomOccupancyCalculator(),
                new PremiumInBoundAndEconomyOutOfBoundRoomOccupancyCalculator(),
                new PremiumOutOfBoundAndEconomyInBoundRoomOccupancyCalculator());
    }

    @Bean
    public IRoomOccupancyCalculatorTypeResolver roomOccupancyCalculatorTypeResolver() {
        return new RoomOccupancyCalculatorTypeResolverImpl();
    }

    @Bean
    public IRoomOccupancyService roomOccupancyService() {
        return new RoomOccupancyServiceImpl(bound, roomOccupancyCalculatorTypeResolver(), roomOccupancyCalculatorFactory(), guestRepository);
    }
}
