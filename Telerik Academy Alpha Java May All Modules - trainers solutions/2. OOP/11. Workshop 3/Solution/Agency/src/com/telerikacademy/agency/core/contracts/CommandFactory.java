package com.telerikacademy.agency.core.contracts;

import com.telerikacademy.agency.commands.contracts.Command;

public interface CommandFactory {
    Command createCommand(String commandTypeAsString, AgencyFactory factory, AgencyRepository agencyRepository);
}
