package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.safetynet.DTO.HomeByStationDTO;
import com.example.safetynet.DTO.PersonsByStationNumberDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;

@Service
public class PersonFirestionMedicalService {

    private PersonFirestationService personFirestationService;

    private PersonMedicalRecordService personMedicalRecordService;

    private FirestationService firestationService;

    private UtilsService utilsService;

    public PersonFirestionMedicalService(PersonFirestationService personFirestationService,
            PersonMedicalRecordService personMedicalRecordService, FirestationService firestationService,
            UtilsService utilsService) {
        this.personFirestationService = personFirestationService;
        this.personMedicalRecordService = personMedicalRecordService;
        this.firestationService = firestationService;
        this.utilsService = utilsService;
    }

    /**
     * Retrieves a list of persons associated with a specific fire station number
     * and categorizes them into adults and children based on their age.
     *
     * @param stationNumber the fire station number to filter persons by
     * @return a PersonsByStationNumberDTO object containing the list of persons,
     *         the number of adults, and the number of children
     */
    public PersonsByStationNumberDTO getPersonsByStationNumberAndAdulteChildByAge(int stationNumber) {
        List<Person> listPersons = personFirestationService.getPersonsByStationNumber(stationNumber);

        PersonsByStationNumberDTO personsByStationNumberDTO = new PersonsByStationNumberDTO(0, 0, listPersons);

        for (Person person : listPersons) {
            MedicalRecord medicalRecord = personMedicalRecordService.getMedicalRecordByPerson(person);
            if (utilsService.calculateAge(medicalRecord.getBirthdate()) <= 18) {
                personsByStationNumberDTO.setNumberChildren(personsByStationNumberDTO.getNumberChildren() + 1);
            } else {
                personsByStationNumberDTO.setNumberAdult(personsByStationNumberDTO.getNumberAdult() + 1);
            }
        }

        return personsByStationNumberDTO;
    }

    /**
     * Retrieves a list of HomeByStationDTO objects for a given address.
     * 
     * This method first fetches the firestation associated with the provided
     * address.
     * It then retrieves a list of persons covered by the firestation's station
     * number.
     * For each person, it checks if their address is already included in the list
     * of HomeByStationDTOs.
     * If the address is not already included, it creates a new HomeByStationDTO
     * object and adds it to the list.
     * 
     * @param address the address to search for
     * @return a list of HomeByStationDTO objects containing information about the
     *         persons and their medical records at the given address
     */
    public List<HomeByStationDTO> getPersonByAddressAddress(String address) {
        Firestation firestation = firestationService.getFirestationByAddress(address);
        List<Person> listPersons = personFirestationService.getPersonsByStationNumber(firestation.getStation());
        List<HomeByStationDTO> listHomeByStationDTOs = new ArrayList<>();

        for (Person person : listPersons) {

            boolean isExist = false;
            for (HomeByStationDTO homeByStationDTO : listHomeByStationDTOs) {
                if (homeByStationDTO.getAddress().equals(person.getAddress())) {
                    isExist = true;
                }
            }
            if (!isExist) {
                HomeByStationDTO homeByStationDTO = new HomeByStationDTO(person.getAddress(), firestation.getStation(),
                        personMedicalRecordService.getPersonsByAddressDTO(person.getAddress()));
                listHomeByStationDTOs.add(homeByStationDTO);
            }

        }
        return listHomeByStationDTOs;
    }

    /**
     * Retrieves a list of households covered by the specified fire station numbers.
     *
     * @param listStationNumber a list of fire station numbers to retrieve
     *                          households for
     * @return a list of HomeByStationDTO objects representing the households
     *         covered by the specified fire stations
     */
    public List<HomeByStationDTO> getHouseholdsByStations(List<Integer> listStationNumber) {

        List<HomeByStationDTO> listHomeByStationDTOs = new ArrayList<>();

        for (int stationNumber : listStationNumber) {

            for (HomeByStationDTO homeByStationDTO : getPersonByAddressAddress(
                    firestationService.getFirestationByStation(stationNumber).getAddress())) {
                listHomeByStationDTOs.add(homeByStationDTO);
            }
        }

        return listHomeByStationDTOs;
    }
}
