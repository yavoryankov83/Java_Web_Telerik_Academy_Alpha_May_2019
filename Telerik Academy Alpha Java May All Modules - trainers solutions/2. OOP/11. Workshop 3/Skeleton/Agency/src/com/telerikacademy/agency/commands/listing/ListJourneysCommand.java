package com.telerikacademy.agency.commands.listing;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.contracts.Journey;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.JOIN_DELIMITER;

public class ListJourneysCommand implements Command {
    private List<Journey> journeys;

    private AgencyRepository agencyRepository;

    public ListJourneysCommand(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public String execute(List<String> parameters) {
        this.journeys = agencyRepository.getJourneys();

        if (journeys.size() == 0) {
            return "There are no registered journeys.";
        }

        List<String> listJourneys = journeysToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listJourneys).trim();
    }

    private List<String> journeysToString() {

        List<String> stringifiedJourneys = new ArrayList<>();
        for (Journey journey : journeys) {
            stringifiedJourneys.add(journey.toString());
        }
        return stringifiedJourneys;
    }
}
