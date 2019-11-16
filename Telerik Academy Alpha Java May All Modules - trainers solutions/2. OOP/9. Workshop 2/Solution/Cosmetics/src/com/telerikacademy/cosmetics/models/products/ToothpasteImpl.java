package com.telerikacademy.cosmetics.models.products;

import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.contracts.Product;
import com.telerikacademy.cosmetics.models.contracts.Toothpaste;

import java.util.List;

public class ToothpasteImpl extends ProductBase implements Toothpaste {
    private List<String> ingredients;

    public ToothpasteImpl(String name, String brand, double price, GenderType gender, List<String> ingredients) {
        super(name, brand, price, gender);
        setIngredients(ingredients);
    }

    private void setIngredients(List<String> ingredients) {
        if (ingredients == null) {
            throw new IllegalArgumentException();
        }
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String printDetails() {
        return String.format(" #Ingredients: %s\n ===", ingredients);
    }
}
