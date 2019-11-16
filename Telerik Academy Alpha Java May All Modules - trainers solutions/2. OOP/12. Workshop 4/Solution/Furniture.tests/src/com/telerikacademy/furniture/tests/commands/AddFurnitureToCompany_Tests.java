package com.telerikacademy.furniture.tests.commands;

import com.telerikacademy.furniture.commands.AddFurnitureToCompany;
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

public class AddFurnitureToCompany_Tests {
    private Command testCommand;
    private Company testCompany;
    private Furniture testFurniture;
    private FurnitureRepository furnitureRepository;
    private FurnitureFactory furnitureFactory;

    @Before
    public void before() {
        furnitureFactory = new FurnitureFactoryImpl();
        furnitureRepository = new FurnitureRepositoryImpl();
        testCommand = new AddFurnitureToCompany(furnitureRepository, furnitureFactory);
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

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_companyDoesNotExist() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("companyName");
        testList.add("model");
        furnitureRepository.addFurniture("model", testFurniture);

        // Act & Assert
        testCommand.execute(testList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_furnitureDoesNotExist() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("companyName");
        testList.add("model");
        furnitureRepository.addCompany("companyName", testCompany);

        // Act & Assert
        testCommand.execute(testList);
    }


    @Test
    public void execute_should_addFurnitureToCompany_whenInputCorrect() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("companyName");
        testList.add("model");
        furnitureRepository.addCompany("companyName", testCompany);
        furnitureRepository.addFurniture("model", testFurniture);

        // Act
        testCommand.execute(testList);

        // Assert
        Assert.assertEquals(1, testCompany.getFurnitures().size());

    }
}
