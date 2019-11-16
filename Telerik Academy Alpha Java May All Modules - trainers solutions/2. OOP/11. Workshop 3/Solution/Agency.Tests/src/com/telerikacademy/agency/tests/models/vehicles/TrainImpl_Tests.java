package com.telerikacademy.agency.tests.models.vehicles;

import com.telerikacademy.agency.models.vehicles.TrainImpl;
import com.telerikacademy.agency.models.vehicles.contracts.Train;
import org.junit.Test;

public class TrainImpl_Tests {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityLessThanMinValue() {
        // Act
        Train train = new TrainImpl(29, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_passengerCapacityMoreThanMaxValue() {
        // Act
        Train train = new TrainImpl(151, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_cartsLessThanMinValue() {
        // Act
        Train train = new TrainImpl(50, 2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_cartsMoreThanMaxValue() {
        // Act
        Train train = new TrainImpl(50, 2, 16);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmLessThanMinimum() {
        // Act
        Train train = new TrainImpl(50, 0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_pricePerKmMoreThanMaximum() {
        // Act
        Train train = new TrainImpl(50, 3, 5);
    }
}
