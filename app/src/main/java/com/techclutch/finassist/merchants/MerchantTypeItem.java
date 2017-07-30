package com.techclutch.finassist.merchants;

/**
 * Created by ArifH_AW17 on 7/30/2017.
 */

public class MerchantTypeItem {
    private String name;
    private String eligibleLoan;
    private String totalRepayment;
    private String interestRate;
    private String eligibleDiscount;
    private Integer imageResource;

    public MerchantTypeItem(String name, String eligibleLoan, String totalRepayment, String interestRate, String eligibleDiscount,Integer imageResource) {
        this.name = name;
        this.eligibleLoan = eligibleLoan;
        this.totalRepayment = totalRepayment;
        this.interestRate = interestRate;
        this.eligibleDiscount = eligibleDiscount;
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

    public String getEligibleLoan() {
        return eligibleLoan;
    }

    public void setEligibleLoan(String eligibleLoan) {
        this.eligibleLoan = eligibleLoan;
    }

    public String getTotalRepayment() {
        return totalRepayment;
    }

    public void SetTotalRepayment(String totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getEligibleDiscount() {
        return eligibleDiscount;
    }

    public void setEligibleDiscount(String eligibleDiscount) {
        this.eligibleDiscount = eligibleDiscount;
    }
}