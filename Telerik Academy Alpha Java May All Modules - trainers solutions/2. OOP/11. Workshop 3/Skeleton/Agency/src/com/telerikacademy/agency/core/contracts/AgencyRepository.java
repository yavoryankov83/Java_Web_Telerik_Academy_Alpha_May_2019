package com.telerikacademy.agency.core.contracts;

import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.contracts.Ticket;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

import java.util.List;

public interface AgencyRepository {
    List<Vehicle> getVehicles();

    List<Journey> getJourneys();

    List<Ticket> getTickets();

    void addVehicle(Vehicle vehicle);

    void addJourney(Journey journey);

    void addTicket(Ticket ticket);
}
