package com.example.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Firestation class represents a fire station with an address and a station
 * number.
 * It is used to map JSON properties to Java fields.
 * 
 * <p>
 * This class provides getter and setter methods for the address and station
 * fields.
 * It also includes a default constructor and a parameterized constructor for
 * creating instances.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * Firestation firestation = new Firestation("123 Main St", 1);
 * String address = firestation.getAddress();
 * int station = firestation.getStation();
 * </pre>
 * 
 * <p>
 * JSON properties:
 * </p>
 * <ul>
 * <li><b>address</b>: The address of the fire station.</li>
 * <li><b>station</b>: The station number of the fire station.</li>
 * </ul>
 * 
 * @see com.fasterxml.jackson.annotation.JsonProperty
 */
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
