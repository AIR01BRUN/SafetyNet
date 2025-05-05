package com.example.safetynet.DTO;

import java.util.List;

public class HomeByStationDTO {

    private String address;
    private int firestationNumber;
    private List<PersonsDTO> listPersonsHome;

    public HomeByStationDTO() {
    }

    public HomeByStationDTO(String address, int firestationNumber, List<PersonsDTO> listPersonsHome) {
        this.address = address;
        this.firestationNumber = firestationNumber;
        this.listPersonsHome = listPersonsHome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFirestationNumber() {
        return firestationNumber;
    }

    public void setFirestationNumber(int firestationNumber) {
        this.firestationNumber = firestationNumber;
    }

    public List<PersonsDTO> getListPersonsHome() {
        return listPersonsHome;
    }

    public void setListPersonsHome(List<PersonsDTO> listPersonsHome) {
        this.listPersonsHome = listPersonsHome;
    }

}
