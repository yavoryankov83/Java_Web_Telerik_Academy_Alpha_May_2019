package com.telerikacademy.agency.commands.listing;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.models.vehicles.contracts.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.agency.commands.CommandsConstants.JOIN_DELIMITER;

public class ListVehiclesCommand implements Command {
    private List<Vehicle> vehicles;

    private AgencyRepository agencyRepository;

    public ListVehiclesCommand(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public String execute(List<String> parameters) {
        vehicles = agencyRepository.getVehicles();

        if (vehicles.size() == 0) {
            return "There are no registered vehicles.";
        }

        List<String> listVehicles = vehiclesToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listVehicles).trim();
    }

    private List<String> vehiclesToString() {
        List<String> stringifiedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            stringifiedVehicles.add(vehicle.toString());
        }
        return stringifiedVehicles;
    }
}
