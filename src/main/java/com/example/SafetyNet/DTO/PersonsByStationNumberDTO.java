package com.example.safetynet.DTO;

import java.util.List;

import com.example.safetynet.model.Person;

public class PersonsByStationNumberDTO {

    private int numberChildren;
    private int numberAdult;
    private List<Person> listPersons;

    public PersonsByStationNumberDTO() {
    }

    public PersonsByStationNumberDTO(int numberChildren, int numberAdult, List<Person> listPersons) {
        this.numberChildren = numberChildren;
        this.numberAdult = numberAdult;
        this.listPersons = listPersons;
    }

    public int getNumberChildren() {
        return numberChildren;
    }

    public void setNumberChildren(int numberChildren) {
        this.numberChildren = numberChildren;
    }

    public int getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(int numberAdult) {
        this.numberAdult = numberAdult;
    }

    public List<Person> getListPersons() {
        return listPersons;
    }

    public void setListPersons(List<Person> listPersons) {
        this.listPersons = listPersons;
    }

}
