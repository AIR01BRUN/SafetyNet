package com.example.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestation {

    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
    private int station;

    public Firestation() {
    }

    public Firestation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

}
