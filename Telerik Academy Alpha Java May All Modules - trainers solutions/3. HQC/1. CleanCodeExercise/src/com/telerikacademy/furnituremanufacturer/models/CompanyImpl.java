package com.telerikacademy.furnituremanufacturer.models;

import com.telerikacademy.furnituremanufacturer.interfaces.Company;
import com.telerikacademy.furnituremanufacturer.interfaces.Furniture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyImpl implements Company {
    private static final int MIN_COMPANY_NAME_LENGTH = 5;
    private static final int EXACT_REGISTRATION_NUMBER_LENGTH = 10;
    private static final String FURNITURE_SINGLE = "furniture";
    private static final String FURNITURE_PLURAL = "furnitures";
    private static final String EMPTY_COUNT = "no";
    private static final String NAME_ERROR_MESSAGE = "Name cannot be less than 5 symbols!";
    private static final String NUMBER_ERROR_MESSAGE = "Registration number is not valid";

    private String name;
    private String registrationNumber;
    private List<Furniture> furnitures;

    public CompanyImpl(String name, String registrationNumber) {
        setName(name);
        setRegistrationNumber(registrationNumber);
        furnitures = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public List<Furniture> getFurnitures() {
        return new ArrayList<>(furnitures);
    }

    @Override
    public String catalog() {
        return String.format(
                "%s - %s - %s %s \n%s",
                name,
                registrationNumber,
                getFurnitureCount(),
                getFurnitureLabel(),
                getFurnitureList()
        );
    }

    @Override
    public void add(Furniture furniture) {
        furnitures.add(furniture);
    }

    @Override
    public Furniture find(String model) {
        return furnitures.stream()
                .filter(x -> x.getModel().equalsIgnoreCase(model))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void remove(Furniture furniture) {
        furnitures.remove(furniture);
    }

    private String getFurnitureList() {
        return furnitures.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    private String getFurnitureLabel() {
        return furnitures.size() == 1 ? FURNITURE_SINGLE : FURNITURE_PLURAL;
    }

    private String getFurnitureCount() {
        return furnitures.isEmpty() ? EMPTY_COUNT : String.format("%d", furnitures.size());
    }

    private void setName(String name) {
        if (name.length() < MIN_COMPANY_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
        this.name = name;
    }

    private void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber.length() != EXACT_REGISTRATION_NUMBER_LENGTH) {
            throw new IllegalArgumentException(NUMBER_ERROR_MESSAGE);
        }
        if (!registrationNumber.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(NUMBER_ERROR_MESSAGE);
        }
        this.registrationNumber = registrationNumber;
    }
}