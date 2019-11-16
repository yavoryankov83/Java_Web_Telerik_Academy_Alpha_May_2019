package com.telerikacademy.agency.models;

import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.contracts.Ticket;

public class TicketImpl implements Ticket {
    private Journey journey;
    private double costs;

    public TicketImpl(Journey journey, double costs) {
        setJourney(journey);
        setCosts(costs);
    }

    @Override
    public double getAdministrativeCosts() {
        return costs;
    }

    @Override
    public Journey getJourney() {
        return journey;
    }

    @Override
    public double calculatePrice() {
        return costs * journey.calculateTravelCosts();
    }

    @Override
    public String print() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("Ticket ----\n" +
                "Destination: %s\n" +
                "Price: %f\n", journey.getDestination(), calculatePrice());
    }

    private void setJourney(Journey journey) {
        ValidationHelper.validateNotNull(journey);
        this.journey = journey;
    }

    private void setCosts(double costs) {
        if (costs < 0) {
            throw new IllegalArgumentException();
        }
        this.costs = costs;
    }
}
