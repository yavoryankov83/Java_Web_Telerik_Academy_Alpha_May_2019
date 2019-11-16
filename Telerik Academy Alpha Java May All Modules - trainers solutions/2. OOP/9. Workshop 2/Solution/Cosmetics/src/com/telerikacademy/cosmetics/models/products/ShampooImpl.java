package com.telerikacademy.cosmetics.models.products;

import com.telerikacademy.cosmetics.models.common.GenderType;
import com.telerikacademy.cosmetics.models.common.UsageType;
import com.telerikacademy.cosmetics.models.contracts.Shampoo;

public class ShampooImpl extends ProductBase implements Shampoo {
    private static final int MILLIMETERS_MIN_VALUE = 0;

    private int milliliters;
    private UsageType usageType;

    public ShampooImpl(String name, String brand, double price, GenderType gender, int milliliters, UsageType usageType) {
        super(name, brand, price, gender);
        setMilliliters(milliliters);
        this.usageType = usageType;
    }

    private void setMilliliters(int milliliters) {
        if (milliliters < MILLIMETERS_MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        this.milliliters = milliliters;
    }

    public int getMilliliters() {
        return milliliters;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    @Override
    public String printDetails() {
        return String.format(" #Milliliters: %s\n #Usage: %s\n ===",
                getMilliliters(), getUsageType());
    }
}
