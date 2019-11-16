package com.telerikacademy.agency.models.vehicles;

import com.telerikacademy.agency.models.ValidationHelper;
import com.telerikacademy.agency.models.vehicles.contracts.Train;

public class TrainImpl extends VehicleBase implements Train {
    private static final int PASSENGER_MIN_VALUE = 30;
    private static final int PASSENGER_MAX_VALUE = 150;
    private static final String PASSENGER_ERROR_MESSAGE = "A train cannot have less than 30 passengers or more than 150 passengers.";
    private static final int CARTS_MIN_VALUE = 1;
    private static final int CARTS_MAX_VALUE = 15;
    private static final String CARTS_ERROR_MESSAGE = "A train cannot have less than 1 cart or more than 15 carts.";

    private int carts;

    public TrainImpl(int passengerCapacity, double pricePerKilometer, int carts) {
        super(passengerCapacity, pricePerKilometer);
        setCarts(carts);
    }

    @Override
    public int getCarts() {
        return carts;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.LAND;
    }

    @Override
    public String print() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Train ----\n" +
                "%s\n" +
                "Carts amount: %d\n", super.toString(), carts);
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

    private void setCarts(int carts) {
        ValidationHelper.validateValueInRange(
                carts,
                CARTS_MIN_VALUE,
                CARTS_MAX_VALUE,
                CARTS_ERROR_MESSAGE
        );
        this.carts = carts;
    }
}
