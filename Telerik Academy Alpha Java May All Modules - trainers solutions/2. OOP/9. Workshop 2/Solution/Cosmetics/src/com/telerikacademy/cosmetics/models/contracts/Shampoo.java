package com.telerikacademy.cosmetics.models.contracts;

import com.telerikacademy.cosmetics.models.common.UsageType;

public interface Shampoo extends Product{
    int getMilliliters();
    UsageType getUsageType();
}
