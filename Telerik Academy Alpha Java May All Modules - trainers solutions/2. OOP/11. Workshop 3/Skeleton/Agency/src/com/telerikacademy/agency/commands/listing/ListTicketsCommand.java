package com.telerikacademy.agency.commands.listing;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.contracts.Ticket;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.JOIN_DELIMITER;

public class ListTicketsCommand implements Command {
    private List<Ticket> tickets;

    private AgencyRepository agencyRepository;

    public ListTicketsCommand(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public String execute(List<String> parameters) {
        tickets = agencyRepository.getTickets();

        if (tickets.size() == 0) {
            return "There are no registered tickets.";
        }

        List<String> listTickets = ticketsToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listTickets).trim();
    }

    private List<String> ticketsToString() {
        List<String> stringifiedTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            stringifiedTickets.add(ticket.toString());
        }
        return stringifiedTickets;
    }
}
