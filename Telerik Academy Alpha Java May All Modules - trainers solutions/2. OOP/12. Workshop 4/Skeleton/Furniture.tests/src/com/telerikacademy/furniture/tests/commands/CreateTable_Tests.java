package com.telerikacademy.furniture.tests.commands;

import com.telerikacademy.furniture.commands.CreateTable;
import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.FurnitureRepositoryImpl;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.core.factories.FurnitureFactoryImpl;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateTable_Tests {
    private Command testCommand;
    private Company testCompany;
    private Furniture testFurniture;
    private FurnitureRepository furnitureRepository;
    private FurnitureFactory furnitureFactory;

    @Before
    public void before() {
        furnitureFactory = new FurnitureFactoryImpl();
        furnitureRepository = new FurnitureRepositoryImpl();
        testCommand = new CreateTable(furnitureRepository, furnitureFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("companyName");
        testList.add("1523232585");

        // Act
        testCommand.execute(testList);

        // Assert
        Company company = furnitureRepository.getCompanies().get("companyName");
        Assert.assertEquals("1523232585", company.getRegistrationNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");

        // Act & Assert
        testCommand.execute(testList);
    }

    @Test
    public void execute_should_createTable_whenPassedValidArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("model");
        testList.add("leather");
        testList.add("4");
        testList.add("4");
        testList.add("4");
        testList.add("4");

        // Act
        testCommand.execute(testList);

        // Arrange
        Assert.assertEquals(1, furnitureRepository.getFurnitures().size());

    }
}
