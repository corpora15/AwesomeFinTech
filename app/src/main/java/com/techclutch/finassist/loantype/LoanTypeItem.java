package com.techclutch.finassist.loantype;

/**
 * Created by Arman on 7/30/2017.
 */

public class LoanTypeItem {
    private String name;
    private Integer imageResource;

    public LoanTypeItem(String name, Integer imageResource) {
        this.name = name;
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
}
