package com.telerikacademy.agency.tests.commands;

import com.telerikacademy.agency.commands.contracts.Command;
import com.telerikacademy.agency.commands.creation.CreateAirplaneCommand;
import com.telerikacademy.agency.commands.creation.CreateTicketCommand;
import com.telerikacademy.agency.core.AgencyRepositoryImpl;
import com.telerikacademy.agency.core.contracts.AgencyFactory;
import com.telerikacademy.agency.core.contracts.AgencyRepository;
import com.telerikacademy.agency.core.factories.AgencyFactoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateTicket_Tests {
    private AgencyRepository agencyRepository;
    private AgencyFactory agencyFactory;
    private Command testCommand;

    @Before
    public void before() {
        this.agencyFactory = new AgencyFactoryImpl();
        this.agencyRepository = new AgencyRepositoryImpl();
        this.testCommand = new CreateTicketCommand(agencyFactory,agencyRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedFewerArgumentsThanExpected() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("5");

        // Act
        testCommand.execute(arguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArgumentsThanExpected() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("5");
        arguments.add("2");
        arguments.add("5");

        // Act
        testCommand.execute(arguments);
    }
    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedInvalidIndex() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("h");
        arguments.add("2");
        agencyRepository.addJourney(null);
        // Act
        testCommand.execute(arguments);
    }
    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedInvalidAdministrativeCosts() {
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("0");
        arguments.add("h");
        agencyRepository.addJourney(null);

        // Act
        testCommand.execute(arguments);
    }

    @Test
    public void execute_should_createTicket_when_passedValidInput(){
        // Arrange
        List<String> arguments = new ArrayList<>();
        arguments.add("0");
        arguments.add("2");
        agencyRepository.addJourney(null);

        // Act
        testCommand.execute(arguments);

        // Assert
        Assert.assertEquals("TicketImpl", agencyRepository.getTickets().get(0).getClass().getSimpleName());
    }

}
