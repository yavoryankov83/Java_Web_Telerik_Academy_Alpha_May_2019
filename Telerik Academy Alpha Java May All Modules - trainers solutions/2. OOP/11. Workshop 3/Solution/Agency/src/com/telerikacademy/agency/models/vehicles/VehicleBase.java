package com.telerikacademy.agency.models.vehicles;

import com.telerikacademy.agency.models.ValidationHelper;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

public abstract class VehicleBase implements Vehicle {
    private static final int PASSENGER_MIN_VALUE = 1;
    private static final int PASSENGER_MAX_VALUE = 800;
    private static final String PASSENGER_ERROR_MESSAGE = "A vehicle with less than 1 passengers or more than 800 passengers cannot exist!";
    private static final double PRICE_MIN_VALUE = 0.1;
    private static final double PRICE_MAX_VALUE = 2.5;
    private static final String PRICE_ERROR_MESSAGE = "A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!";

    private int passengerCapacity;
    private double pricePerKilometer;

    public VehicleBase(int passengerCapacity, double pricePerKilometer) {
        setPassengerCapacity(passengerCapacity);
        setPricePerKilometer(pricePerKilometer);
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    @Override
    public String print() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Passenger capacity: %d\n" +
                "Price per kilometer: %f\n" +
                "Vehicle type: %s", passengerCapacity, pricePerKilometer, getType());
    }

    protected int getPassengerMinValue() {
        return PASSENGER_MIN_VALUE;
    }

    protected int getPassengerMaxValue() {
        return PASSENGER_MAX_VALUE;
    }

    protected String getPassengerErrorMessage() {
        return PASSENGER_ERROR_MESSAGE;
    }

    protected double getPriceMinValue() {
        return PRICE_MIN_VALUE;
    }

    protected double getPriceMaxValue() {
        return PRICE_MAX_VALUE;
    }

    protected String getPriceErrorMessage() {
        return PRICE_ERROR_MESSAGE;
    }

    private void setPassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(
                passengerCapacity,
                getPassengerMinValue(),
                getPassengerMaxValue(),
                getPassengerErrorMessage());
        this.passengerCapacity = passengerCapacity;
    }

    private void setPricePerKilometer(double pricePerKilometer) {
        ValidationHelper.validateValueInRange(
                pricePerKilometer,
                getPriceMinValue(),
                getPriceMaxValue(),
                getPriceErrorMessage());
        this.pricePerKilometer = pricePerKilometer;
    }
}
