package com.telerikacademy.agency.commands.creation;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateJourneyCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private final AgencyFactory factory;
    private final AgencyRepository agencyRepository;
    private String startLocation;
    private String destination;
    private int distance;
    private Vehicle vehicle;

    public CreateJourneyCommand(AgencyFactory factory, AgencyRepository agencyRepository) {
        this.factory = factory;
        this.agencyRepository = agencyRepository;
    }

    public String execute(List<String> parameters) {
        validateInput(parameters);

        parseParameters(parameters);

        Journey journey = factory.createJourney(startLocation, destination, distance, vehicle);
        agencyRepository.addJourney(journey);

        return String.format("Journey with ID %d was created.", agencyRepository.getJourneys().size() - 1);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(String.format(
                    INVALID_NUMBER_OF_ARGUMENTS,
                    EXPECTED_NUMBER_OF_ARGUMENTS,
                    parameters.size()));
        }
    }

    private void parseParameters(List<String> parameters) {
        try {
            startLocation = parameters.get(0);
            destination = parameters.get(1);
            distance = Integer.parseInt(parameters.get(2));
            vehicle = agencyRepository.getVehicles().get(Integer.parseInt(parameters.get(3)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CreateJourney command parameters.");
        }
    }
}
