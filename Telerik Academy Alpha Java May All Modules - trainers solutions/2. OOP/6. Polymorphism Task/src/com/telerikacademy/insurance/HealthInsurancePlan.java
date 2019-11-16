package com.telerikacademy.insurance;

public class HealthInsurancePlan {
    private double coverage;
    private int discount;

    public HealthInsurancePlan(double coverage, int discount) {
        setCoverage(coverage);
        setDiscount(discount);
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
