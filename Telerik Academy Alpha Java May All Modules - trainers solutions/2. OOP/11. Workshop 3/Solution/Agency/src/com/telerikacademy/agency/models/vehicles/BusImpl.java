package com.telerikacademy.agency.models.vehicles;

import com.telerikacademy.agency.models.vehicles.contracts.Bus;

public class BusImpl extends VehicleBase implements Bus {
    private static final int PASSENGER_MIN_VALUE = 10;
    private static final int PASSENGER_MAX_VALUE = 50;
    private static final String PASSENGER_ERROR_MESSAGE = "A bus cannot have less than 10 passengers or more than 50 passengers.";

    public BusImpl(int passengerCapacity, double pricePerKilometer) {
        super(passengerCapacity, pricePerKilometer);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.LAND;
    }

    @Override
    public String toString() {
        return String.format("Bus ----\n" +
                "%s\n", super.toString());
    }

    @Override
    protected int getPassengerMinValue() {
        return PASSENGER_MIN_VALUE;
    }

    @Override
    protected int getPassengerMaxValue() {
        return PASSENGER_MAX_VALUE;
    }

    @Override
    protected String getPassengerErrorMessage() {
        return PASSENGER_ERROR_MESSAGE;
    }
}
