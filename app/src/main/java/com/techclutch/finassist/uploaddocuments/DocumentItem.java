package com.techclutch.finassist.uploaddocuments;

/**
 * Created by madzirin on 7/30/2017.
 */

public class DocumentItem {
    private String name;
    private Integer imageResource;
    private boolean isCompleted;

    public DocumentItem(String name, Integer imageResource) {
        this.name = name;
        this.imageResource = imageResource;
        this.isCompleted = false;
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

    public boolean getIsCompleted() { return isCompleted; }

    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }
}
