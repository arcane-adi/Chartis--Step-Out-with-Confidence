package com.example.maptyxf;

import com.google.firebase.Timestamp;

public class Incident {

    String IncidentPlace;
    String Type;
    String description;
    Timestamp timestamp;

    public Incident() {
    }
    public String getIncidentPlacePlace() {
        return IncidentPlace;
    }

    public void setIncidentPlace(String IncidentPlace) {
        this.IncidentPlace = IncidentPlace;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
