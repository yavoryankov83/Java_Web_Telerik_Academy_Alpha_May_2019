package com.telerikacademy.insurance;

public class SilverPlan extends HealthInsurancePlan {
    private static final double PREMIUM = 0.06;

    public SilverPlan() {
        super(0.7, PREMIUM, 30);
    }
}
