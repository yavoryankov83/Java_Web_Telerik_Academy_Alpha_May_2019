package com.telerikacademy.insurance;

public class HealthInsurancePlan {
    private double coverage;
    private double premium;
    private int discount;
    private InsuranceBrand offeredBy;

    public HealthInsurancePlan(double coverage, double premium, int discount) {
        setCoverage(coverage);
        setPremium(premium);
        setDiscount(discount);
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public double computeMonthlyPremium(double salary) {
        return salary * premium;
    }
}
