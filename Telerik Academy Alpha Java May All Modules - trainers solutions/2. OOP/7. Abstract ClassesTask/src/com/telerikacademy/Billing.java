package com.telerikacademy;

import com.telerikacademy.insurance.BronzePlan;
import com.telerikacademy.insurance.HealthInsurancePlan;
import com.telerikacademy.users.Patient;

public class Billing {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] result = new double[2];

        double coverage = 0;
        HealthInsurancePlan healthInsurancePlan = patient.getInsurancePlan();
        if (healthInsurancePlan != null) {
            coverage = healthInsurancePlan.getCoverage();
        }

        result[0] = amount * coverage;
        result[1] = amount - result[0];

        int discount = 20;
        if (healthInsurancePlan != null) {
            discount = healthInsurancePlan.getDiscount();
        }

        result[1] = result[1] - discount;
        if (result[1] < 0) {
            result[1] = 0;
        }

        return result;
    }
}
