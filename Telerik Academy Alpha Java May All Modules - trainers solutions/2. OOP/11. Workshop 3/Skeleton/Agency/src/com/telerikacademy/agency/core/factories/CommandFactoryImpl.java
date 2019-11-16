package com.telerikacademy.agency.core.factories;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.commands.creation.*;
import com.telerikacademy.agency.commands.listing.ListJourneysCommand;
import com.telerikacademy.agency.commands.listing.ListTicketsCommand;
import com.telerikacademy.agency.commands.listing.ListVehiclesCommand;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.core.contracts.CommandFactory;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.commands.enums.CommandType;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommand(String commandName, AgencyFactory agencyFactory, AgencyRepository agencyRepository) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {

            case CREATEBUS:
                return new CreateBusCommand(agencyFactory, agencyRepository);

            case CREATEJOURNEY:
                return new CreateJourneyCommand(agencyFactory, agencyRepository);

            case CREATETRAIN:
                return new CreateTrainCommand(agencyFactory, agencyRepository);

            case LISTJOURNEYS:
                return new ListJourneysCommand(agencyRepository);

            case LISTTICKETS:
                return new ListTicketsCommand(agencyRepository);

        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }
}
