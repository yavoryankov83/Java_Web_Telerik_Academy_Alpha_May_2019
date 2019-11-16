package com.telerikacademy.agency.models;

import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

public class JourneyImpl implements Journey {
    private static final int LOCATION_MIN_VALUE = 5;
    private static final int LOCATION_MAX_VALUE = 25;
    private static final String START_LOCATION_ERROR_MESSAGE = "The StartingLocation's length cannot be less than 5 or more than 25 symbols long.";
    private static final String DESTINATION_ERROR_MESSAGE = "The Destination's length cannot be less than 5 or more than 25 symbols long.";
    private static final int DISTANCE_MIN_VALUE = 5;
    private static final int DISTANCE_MAX_VALUE = 5000;
    private static final String DISTANCE_ERROR_MESSAGE = "The Distance cannot be less than 5 or more than 5000 kilometers.";

    private String startLocation;
    private String destination;
    private int distance;
    private Vehicle vehicle;

    public JourneyImpl(String startLocation, String destination, int distance, Vehicle vehicle) {
        setStartLocation(startLocation);
        setDestination(destination);
        setDistance(distance);
        setVehicle(vehicle);
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public String getStartLocation() {
        return startLocation;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public double calculateTravelCosts() {
        return distance * vehicle.getPricePerKilometer();
    }

    @Override
    public String print() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Journey ----\n" +
                "Start location: %s\n" +
                "Destination: %s\n" +
                "Distance: %d\n" +
                "Vehicle type: %s\n" +
                "Travel costs: %f\n", startLocation, destination, distance, vehicle.getType(), calculateTravelCosts());
    }

    private void setStartLocation(String startLocation) {
        ValidationHelper.validateNotNull(startLocation);
        ValidationHelper.validateValueInRange(
                startLocation.length(),
                LOCATION_MIN_VALUE,
                LOCATION_MAX_VALUE,
                START_LOCATION_ERROR_MESSAGE
        );
        this.startLocation = startLocation;
    }

    private void setDestination(String destination) {
        ValidationHelper.validateNotNull(destination);
        ValidationHelper.validateValueInRange(
                destination.length(),
                LOCATION_MIN_VALUE,
                LOCATION_MAX_VALUE,
                DESTINATION_ERROR_MESSAGE
        );
        this.destination = destination;
    }

    private void setDistance(int distance) {
        ValidationHelper.validateValueInRange(
                distance,
                DISTANCE_MIN_VALUE,
                DISTANCE_MAX_VALUE,
                DISTANCE_ERROR_MESSAGE
        );
        this.distance = distance;
    }

    private void setVehicle(Vehicle vehicle) {
        ValidationHelper.validateNotNull(vehicle);
        this.vehicle = vehicle;
    }
}
