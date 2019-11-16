package com.telerikacademy.furniture.tests.commands;

import com.telerikacademy.furniture.commands.FindFurnitureFromCompany;
import com.telerikacademy.furniture.commands.contracts.Command;
import com.telerikacademy.furniture.core.FurnitureRepositoryImpl;
import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.contracts.FurnitureRepository;
import com.telerikacademy.furniture.core.factories.FurnitureFactoryImpl;
import com.telerikacademy.furniture.models.ChairImpl;
import com.telerikacademy.furniture.models.CompanyImpl;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindFurnitureFromCompany_Tests {
    private Command testCommand;
    private Company testCompany;
    private Furniture testFurniture;
    private FurnitureRepository furnitureRepository;
    private FurnitureFactory furnitureFactory;

    @Before
    public void before() {
        furnitureFactory = new FurnitureFactoryImpl();
        furnitureRepository = new FurnitureRepositoryImpl();
        testCommand = new FindFurnitureFromCompany(furnitureRepository, furnitureFactory);
        testCompany = new CompanyImpl("companyName", "1234567890");
        testFurniture = new ChairImpl("model", MaterialType.LEATHER, 4, 4, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("asdasd");

        // Act & Assert
        testCommand.execute(testList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("asdasd");
        testList.add("asdasd");
        testList.add("asdasd");


        // Act & Assert
        testCommand.execute(testList);
    }

    @Test
    public void execute_should_findFurniture_when_passedCorrectInput() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("companyName");
        testList.add("model");
        furnitureRepository.addFurniture("model", testFurniture);
        furnitureRepository.addCompany("companyName", testCompany);
        testCompany.add(testFurniture);

        // Act
        String commandResult = testCommand.execute(testList);
        String expected = testFurniture.toString();

        // Assert
        Assert.assertEquals(expected, commandResult);
    }
}
