package com.telerikacademy.agency.core.factories;

import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.models.JourneyImpl;
import com.telerikacademy.agency.models.TicketImpl;
import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.contracts.Ticket;
import com.telerikacademy.agency.models.vehicles.AirplaneImpl;
import com.telerikacademy.agency.models.vehicles.BusImpl;
import com.telerikacademy.agency.models.vehicles.TrainImpl;
import com.telerikacademy.agency.models.vehicles.contracts.Airplane;
import com.telerikacademy.agency.models.vehicles.contracts.Bus;
import com.telerikacademy.agency.models.vehicles.contracts.Train;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

public class AgencyFactoryImpl implements AgencyFactory {
    public AgencyFactoryImpl() {
    }

    public Bus createBus(int passengerCapacity, double pricePerKilometer) {
        return new BusImpl(passengerCapacity, pricePerKilometer);
    }

    public Airplane createAirplane(int passengerCapacity, double pricePerKilometer, boolean hasFreeFood) {
        return new AirplaneImpl(passengerCapacity, pricePerKilometer, hasFreeFood);
    }

    public Train createTrain(int passengerCapacity, double pricePerKilometer, int carts) {
        return new TrainImpl(passengerCapacity, pricePerKilometer, carts);
    }

    public Journey createJourney(String startLocation, String destination, int distance, Vehicle vehicle) {
        return new JourneyImpl(startLocation, destination, distance, vehicle);
    }

    public Ticket createTicket(Journey journey, double administrativeCosts) {
        return new TicketImpl(journey, administrativeCosts);
    }
}
