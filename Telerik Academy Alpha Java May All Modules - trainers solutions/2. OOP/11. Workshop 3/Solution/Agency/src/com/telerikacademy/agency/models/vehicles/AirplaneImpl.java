package com.telerikacademy.agency.models.vehicles;

import com.telerikacademy.agency.models.vehicles.contracts.Airplane;

public class AirplaneImpl extends VehicleBase implements Airplane {
    private boolean hasFreeFood;

    public AirplaneImpl(int passengerCapacity, double pricePerKilometer, boolean hasFreeFood) {
        super(passengerCapacity, pricePerKilometer);
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public boolean hasFreeFood() {
        return hasFreeFood;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.AIR;
    }

    @Override
    public String toString() {
        return String.format("Airplane ----\n" +
                "%s\n" +
                "Has free food: %s\n", super.toString(), hasFreeFood);
    }
}
