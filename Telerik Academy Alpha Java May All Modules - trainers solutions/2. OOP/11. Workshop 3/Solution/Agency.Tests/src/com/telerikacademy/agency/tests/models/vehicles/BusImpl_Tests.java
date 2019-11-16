package com.telerikacademy.agency.tests.models.vehicles;

import com.telerikacademy.agency.models.vehicles.BusImpl;
import com.telerikacademy.agency.models.vehicles.contracts.Bus;
import org.junit.Test;

public class BusImpl_Tests {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityLessThanMinValue() {
        // Act
        Bus bus = new BusImpl(9, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityMoreThanMaxValue() {
        // Act
        Bus bus = new BusImpl(51, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
        // Act
        Bus bus = new BusImpl(25, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act
        Bus bus = new BusImpl(25, 3);
    }
}
