package com.techclutch.finassist.banktypes;

/**
 * Created by Arman on 7/30/2017.
 */

public class BankTypeItem {
    private String name;
    private String monthlyPayment;
    private String totalPayment;
    private String interestRate;
    private Integer imageResource;

    public BankTypeItem(String name, String monthlyPayment, String totalPayment, String interestRate, Integer imageResource) {
        this.name = name;
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.interestRate = interestRate;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }
}
