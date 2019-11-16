package com.telerikacademy.agency.core;

import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.contracts.Ticket;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AgencyRepositoryImpl implements AgencyRepository {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Journey> journeys = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public List<Journey> getJourneys() {
        return new ArrayList<>(journeys);
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void addJourney(Journey journey) {
        this.journeys.add(journey);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
