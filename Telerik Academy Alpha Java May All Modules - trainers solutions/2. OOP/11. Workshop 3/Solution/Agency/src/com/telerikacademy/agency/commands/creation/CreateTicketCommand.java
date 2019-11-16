package com.telerikacademy.agency.commands.creation;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.contracts.Journey;
import com.telerikacademy.agency.models.contracts.Ticket;
import com.telerikacademy.agency.models.vehicles.contracts.Bus;

import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateTicketCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final AgencyFactory factory;
    private final AgencyRepository agencyRepository;

    public CreateTicketCommand(AgencyFactory factory, AgencyRepository agencyRepository) {
        this.factory = factory;
        this.agencyRepository = agencyRepository;
    }

    private int journeyId;
    private double cost;

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        parseParameters(parameters);

        Journey journey = agencyRepository.getJourneys().get(journeyId);
        Ticket ticket = factory.createTicket(journey, cost);
        agencyRepository.addTicket(ticket);

        return String.format("Ticket with ID %d was created.", agencyRepository.getTickets().size() - 1);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, EXPECTED_NUMBER_OF_ARGUMENTS, parameters.size()));
        }
    }

    private void parseParameters(List<String> parameters) {
        try {
            journeyId = Integer.parseInt(parameters.get(0));
            cost = Double.parseDouble(parameters.get(1));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CreateTicket command parameters.");
        }
    }
}
