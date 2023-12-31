# Solution Room Occupancy Manager

## Description:
Application is written by using SOLID principles, DDD and also clean architecture (ports and adapters) where domain is completely separated from framework.
I also used design patterns like factory and strategy for clean code purposes.
There also tests to check appropriate code working: interfaces layer, domain layer and unit.

## Workflow:
1. Guests are saved in database
2. Client sends request to the server on endpoint POST "room-occupancy" with body: freeEconomyRooms, freePremiumRooms
3. Server calculates rooms occupancy for guests represented by prices combination with "room-occupancy" body
4. Server responds with calculated values: economyUsage, economyTotalPrice, premiumUsage, premiumTotalPrice

## Environment variables:
In [application.yaml](src%2Fmain%2Fresources%2Fapplication.yaml):
- "bound-price" is responsible for bound price where economy rooms are finish and premium start.
- "initialize-guests" is responsible for initialization guests 
You can adjust them according to your needs.

## Error handling:
In the app only MethodArgumentNotValidException is handled because client shouldn't know about any other:
- [BadBoundPriceValueException.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Froomoccupancymanager%2Fdomain%2Fexception%2FBadBoundPriceValueException.java) - bound price value is set internally, part of business logic
- [UnsupportedRoomOccupancyCalculatorTypeException.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Froomoccupancymanager%2Fdomain%2Fexception%2FUnsupportedRoomOccupancyCalculatorTypeException.java) - client shouldn't know about internal process in business logic

## Requirements:
- java 17

## Run build:
```
mvn clean install
```

## Run app:
```
mvn spring-boot:run
```

## Run tests:
```
mvn test
```