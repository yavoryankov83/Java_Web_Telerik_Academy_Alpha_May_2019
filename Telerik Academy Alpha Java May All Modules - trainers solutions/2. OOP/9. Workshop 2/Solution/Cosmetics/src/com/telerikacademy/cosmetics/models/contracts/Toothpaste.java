package com.telerikacademy.cosmetics.models.contracts;

import java.util.List;

public interface Toothpaste extends Product {
    List<String> getIngredients();
}
