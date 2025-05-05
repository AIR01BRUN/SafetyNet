package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.safetynet.model.Firestation;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FirestationRepository;
import com.example.safetynet.repository.PersonRepositoty;

@Service
public class PersonFirestationService {

    private final FirestationRepository firestationRepository;
    private final PersonRepositoty personRepository;

    public PersonFirestationService(FirestationRepository firestationRepository, PersonRepositoty personRepository) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
    }

    /**
     * Retrieves a list of persons who are covered by a specific fire station
     * number.
     *
     * @param stationNumber the fire station number to filter persons by
     * @return a list of persons who are covered by the specified fire station
     *         number
     */
    public List<Person> getPersonsByStationNumber(int stationNumber) {
        List<Person> listPersons = personRepository.findAll();
        List<Firestation> lisFirestations = firestationRepository.findAll();

        List<Person> listPersonsByStationNumber = new ArrayList<>();
        for (Person person : listPersons) {
            for (Firestation firestation : lisFirestations) {
                if (person.getAddress().equals(firestation.getAddress()) && firestation.getStation() == stationNumber) {
                    listPersonsByStationNumber.add(person);
                }
            }

        }
        return listPersonsByStationNumber;
    }

    /**
     * Retrieves a list of phone numbers for all persons associated with a given
     * fire station number.
     *
     * @param firestationNumber the number of the fire station to get phone numbers
     *                          for
     * @return a list of phone numbers of persons covered by the specified fire
     *         station
     */
    public List<String> getPhoneNumber(int firestationNumber) {
        List<String> listPhoneNumber = new ArrayList<>();

        for (Person person : getPersonsByStationNumber(firestationNumber)) {
            listPhoneNumber.add(person.getPhone());
        }
        return listPhoneNumber;
    }

}
