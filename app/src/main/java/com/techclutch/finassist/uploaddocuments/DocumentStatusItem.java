package com.techclutch.finassist.uploaddocuments;

/**
 * Created by Madzirin on 7/30/2017.
 */

public class DocumentStatusItem {
    private Integer imageDocumentMissing; // maybe show camera icon
    private Integer imageDocumentCompleted;

    public DocumentStatusItem(Integer imageDocumentMissing, Integer imageDocumentCompleted) {
        this.imageDocumentMissing = imageDocumentMissing;
        this.imageDocumentCompleted = imageDocumentCompleted;
    }

    public Integer setImageDocumentMissing() {
        return imageDocumentMissing;
    }

    public void getImageDocumentMissing(Integer imageDocumentMissing) {
        this.imageDocumentMissing = imageDocumentMissing;
    }

    public Integer setImageDocumentCompleted() {
        return imageDocumentCompleted;
    }

    public void setImageDocumentCompleted(Integer imageDocumentCompleted) {
        this.imageDocumentCompleted = imageDocumentCompleted;
    }
}
