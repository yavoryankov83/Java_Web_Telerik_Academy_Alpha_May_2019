package com.telerikacademy.insurance;

public class GoldPlan extends HealthInsurancePlan {
    private static final double PREMIUM = 0.07;

    public GoldPlan() {
        super(0.8, PREMIUM, 40);
    }
}
