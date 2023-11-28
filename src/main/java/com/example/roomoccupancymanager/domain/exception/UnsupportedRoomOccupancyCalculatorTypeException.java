package com.example.roomoccupancymanager.domain.exception;

public class UnsupportedRoomOccupancyCalculatorTypeException extends RoomOccupancyException {

        private final String calculatorType;

        public UnsupportedRoomOccupancyCalculatorTypeException(String calculatorType) {
            super(String.format("Unsupported room occupancy calculator type: %s", calculatorType));
            this.calculatorType = calculatorType;
        }

        public String getCalculatorType() {
            return calculatorType;
        }
}
