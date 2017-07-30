package com.techclutch.finassist.uploaddocuments;

/**
 * Created by madzirin on 7/30/2017.
 */

public class DocumentItem {
    private String name;
    private boolean isCompleted;

    public DocumentItem(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsCompleted() { return isCompleted; }

    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }
}
