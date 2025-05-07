package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.safetynet.DTO.AllChildByAddressDTO;
import com.example.safetynet.DTO.ChildByAddressDTO;
import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;

@Service
public class PersonMedicalRecordService {

    private PersonService personService;

    private UtilsService utilsService;

    private MedicalRecordService medicalRecordService;

    public PersonMedicalRecordService(PersonService personService, MedicalRecordService medicalRecordService,
            UtilsService utilsService) {
        this.personService = personService;
        this.utilsService = utilsService;
        this.medicalRecordService = medicalRecordService;

    }

    /**
     * Retrieves the medical record associated with a given person.
     *
     * @param person the person whose medical record is to be retrieved
     * @return the medical record of the specified person, or an empty MedicalRecord
     *         object if no matching record is found
     */

    public MedicalRecord getMedicalRecordByPerson(Person person) {

        MedicalRecord medicalRecordPerson = new MedicalRecord();

        for (MedicalRecord medicalRecord : medicalRecordService.getAllMedicalRecord()) {
            if (medicalRecord.getFirstName().equals(person.getFirstName())
                    && medicalRecord.getLastName().equals(person.getLastName())) {
                medicalRecordPerson = medicalRecord;
            }
        }
        return medicalRecordPerson;

    }

    /**
     * Retrieves a list of children living at the specified address along with the
     * other household members.
     *
     * @param address the address to search for children
     * @return an AllChildByAddressDTO object containing a list of children and a
     *         list of other household members
     */
    public AllChildByAddressDTO getChildByAddress(String address) {

        List<Person> homeMember = new ArrayList<>();
        List<ChildByAddressDTO> lisChildByAddressDTO = new ArrayList<>();

        for (Person person : personService.getPersonsByAddress(address)) {
            MedicalRecord medicalRecord = getMedicalRecordByPerson(person);
            if (utilsService.calculateAge(medicalRecord.getBirthdate()) <= 18) {

                ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO(person.getFirstName(),
                        person.getLastName(), utilsService.calculateAge(medicalRecord.getBirthdate()));

                lisChildByAddressDTO.add(childByAddressDTO);
            } else {
                homeMember.add(person);
            }
        }
        return new AllChildByAddressDTO(lisChildByAddressDTO, homeMember);
    }

    /**
     * Retrieves a list of PersonsDTO objects for a given address.
     *
     * @param address the address to search for persons
     * @return a list of PersonsDTO objects containing details of persons residing
     *         at the specified address
     */
    public List<PersonsDTO> getPersonsByAddressDTO(String address) {
        List<PersonsDTO> listPersonsByAddressDTOs = new ArrayList<>();
        for (Person person : personService.getPersonsByAddress(address)) {
            MedicalRecord medicalRecord = getMedicalRecordByPerson(person);

            PersonsDTO personsByAddressDTO = new PersonsDTO(person.getFirstName(),
                    person.getLastName(), person.getPhone(), utilsService.calculateAge(medicalRecord.getBirthdate()),
                    medicalRecord.getMedications(), medicalRecord.getAllergies());
            listPersonsByAddressDTOs.add(personsByAddressDTO);

        }
        return listPersonsByAddressDTOs;
    }

    /**
     * Retrieves a list of PersonsDTO objects by the specified last name.
     *
     * @param lastName the last name to filter the persons by
     * @return a list of PersonsDTO objects that match the specified last name
     */
    public List<PersonsDTO> getPersonsDTOByLastName(String lastName) {

        List<PersonsDTO> listPersonsDTOByLastName = new ArrayList<>();

        for (Person person : personService.getAllPersons()) {

            if (person.getLastName().equals(lastName)) {
                PersonsDTO personsDTO = new PersonsDTO(person.getFirstName(), person.getLastName(), person.getPhone(),
                        utilsService.calculateAge(getMedicalRecordByPerson(person).getBirthdate()),
                        getMedicalRecordByPerson(person).getMedications(),
                        getMedicalRecordByPerson(person).getAllergies());
                listPersonsDTOByLastName.add(personsDTO);
            }
        }
        return listPersonsDTOByLastName;
    }

}
