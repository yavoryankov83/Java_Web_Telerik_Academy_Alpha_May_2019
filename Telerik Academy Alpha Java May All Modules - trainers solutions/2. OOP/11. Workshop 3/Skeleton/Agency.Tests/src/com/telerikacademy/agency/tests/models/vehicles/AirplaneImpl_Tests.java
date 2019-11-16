package com.telerikacademy.agency.tests.models.vehicles;

import com.telerikacademy.agency.models.vehicles.AirplaneImpl;
import org.junit.Test;

public class AirplaneImpl_Tests {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityLessThanMinValue() {
        // Act
        AirplaneImpl plane = new AirplaneImpl(0, 2, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityMoreThanMaxValue() {
        // Act
        AirplaneImpl plane = new AirplaneImpl(801, 2, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
        // Act
        AirplaneImpl plane = new AirplaneImpl(100, 0, true);

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act
        AirplaneImpl plane = new AirplaneImpl(100, 3, true);

    }
}
