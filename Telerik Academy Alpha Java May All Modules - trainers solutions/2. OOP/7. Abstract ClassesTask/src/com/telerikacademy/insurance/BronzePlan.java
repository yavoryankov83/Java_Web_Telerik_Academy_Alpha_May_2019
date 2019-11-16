package com.telerikacademy.insurance;

public class BronzePlan extends HealthInsurancePlan {
    private static final double PREMIUM = 0.05;

    public BronzePlan() {
        super(0.6, PREMIUM, 25);
    }
}
