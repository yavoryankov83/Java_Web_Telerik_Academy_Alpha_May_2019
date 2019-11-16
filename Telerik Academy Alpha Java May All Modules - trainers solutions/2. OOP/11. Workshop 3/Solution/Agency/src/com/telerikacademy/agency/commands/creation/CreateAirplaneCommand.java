package com.telerikacademy.agency.commands.creation;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.vehicles.contracts.Airplane;

import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateAirplaneCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private final AgencyFactory factory;
    private final AgencyRepository agencyRepository;

    public CreateAirplaneCommand(AgencyFactory factory, AgencyRepository agencyRepository) {
        this.factory = factory;
        this.agencyRepository = agencyRepository;
    }

    private int passengerCapacity;
    private double pricePerKilometer;
    private boolean hasFreeFood;

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        parseParameters(parameters);

        Airplane airplane = factory.createAirplane(passengerCapacity, pricePerKilometer, hasFreeFood);
        agencyRepository.addVehicle(airplane);

        return String.format("Vehicle with ID %d was created.", agencyRepository.getVehicles().size() - 1);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
        }
    }

    private void parseParameters(List<String> parameters) {
        try {
            passengerCapacity = Integer.parseInt(parameters.get(0));
            pricePerKilometer = Double.parseDouble(parameters.get(1));
            hasFreeFood = Boolean.parseBoolean(parameters.get(2));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CreateAirplane command parameters.");
        }
    }
}
