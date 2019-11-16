package com.telerikacademy.agency.tests.models;

import com.telerikacademy.agency.models.JourneyImpl;
import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.vehicles.BusImpl;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;
import org.junit.Before;
import org.junit.Test;

public class Journey_Tests {
    private Vehicle testVehicle;

    @Before
    public void before() {
        testVehicle = new BusImpl(14, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeStartLocationLengthLessThanMinimum() {
        // Act
        Journey journey = new JourneyImpl("Sofi", "Varna", 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeDestinationLengthLessThanMinimum() {
        // Act
        Journey journey = new JourneyImpl("Sofia", "Varn", 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeStartLocationIsNull() {
        // Act
        Journey journey = new JourneyImpl(null, "Varna", 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeDestinationIsNull() {
        // Act
        Journey journey = new JourneyImpl("Sofia", null, 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeStartLocationLengthMoreThanMaximum() {
        // Act
        Journey journey = new JourneyImpl("Sofiadsdsaadsadsassadadsadsadssadadsadssadsadadsadssad", "Varna", 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_placeDestinationLengthMoreThanMaximum() {
        // Act
        Journey journey = new JourneyImpl("Sofia", "Varnasdadsadsadsadsadsadsasasasasdadsasdadsadsadsadsasdads", 44, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_distanceLessThanMinimum() {
        // Act
        Journey journey = new JourneyImpl("Sofia", "Varna", 4, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_distanceMoreThanMaximum() {
        // Act
        Journey journey = new JourneyImpl("Sofia", "Varna", 5001, testVehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_when_vehicleIsNull() {
        // Act
        Journey journey = new JourneyImpl("Sofia", "Varna", 50, null);
    }
}
