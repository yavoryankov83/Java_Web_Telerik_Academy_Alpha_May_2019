package com.telerikacademy.furniture.models;

import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyImpl implements Company {
    private static final int NAME_MIN_LENGTH = 5;

    private String name;
    private String registrationNumber;
    private List<Furniture> furnitures;

    public CompanyImpl(String name, String registrationNumber) {
        setName(name);
        setRegistrationNumber(registrationNumber);
        furnitures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public List<Furniture> getFurnitures() {
        return new ArrayList<>(furnitures);
    }

    public void add(Furniture furniture) {
        furnitures.add(furniture);
    }

    public String catalog() {
        sortFurniture();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("%s - %s - ", name, registrationNumber));
        if (furnitures.isEmpty()) {
            strBuilder.append("no");
        } else {
            strBuilder.append(furnitures.size());
        }
        strBuilder.append(" furniture");
        if (furnitures.size() != 1) {
            strBuilder.append("s");
        }
        strBuilder.append("\n");
        for (Furniture furniture : furnitures) {
            strBuilder.append(furniture);
            strBuilder.append("\n");
        }
        return strBuilder.toString().trim();
    }

    public Furniture find(String model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }

        return furnitures.stream()
                .filter(f -> f.getModel().equalsIgnoreCase(model))
                .findFirst()
                .orElse(null);
    }

    public void remove(Furniture furniture) {
        furnitures.remove(furniture);
    }

    private void setName(String name) {
        ValidationHelper.checkValueMinLength(name, NAME_MIN_LENGTH);
        this.name = name;
    }

    private void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.length() != 10) {
            throw new IllegalArgumentException();
        }
        if (!registrationNumber.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException();
        }
        this.registrationNumber = registrationNumber;
    }

    private void sortFurniture() {
        Collections.sort(furnitures);
//        furnitures.sort(
//                Comparator.comparing(Furniture::getPrice)
//                        .thenComparing(Furniture::getModel));
    }
}
