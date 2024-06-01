package com.example.maptyxf;

import com.google.firebase.Timestamp;

public class Note {

    String place;
    String Review;
    Timestamp timestamp;

    public Note() {
    }
    public String getplace() {
        return place;
    }

    public void setplace(String title) {
        this.place = title;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String content) {
        this.Review = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
