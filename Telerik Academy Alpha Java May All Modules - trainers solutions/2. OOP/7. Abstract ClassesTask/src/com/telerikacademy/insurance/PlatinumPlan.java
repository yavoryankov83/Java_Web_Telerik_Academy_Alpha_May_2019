package com.telerikacademy.insurance;

public class PlatinumPlan extends HealthInsurancePlan {
    private static final double PREMIUM = 0.08;

    public PlatinumPlan() {
        super(0.9, PREMIUM, 50);
    }
}
