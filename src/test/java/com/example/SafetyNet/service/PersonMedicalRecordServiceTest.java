package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.DTO.AllChildByAddressDTO;
import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;

class PersonMedicalRecordServiceTest {

    @Mock
    private PersonService personService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    private UtilsService utilsService;

    @InjectMocks
    private PersonMedicalRecordService personMedicalRecordService;

    private List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
    private List<Person> persons = new ArrayList<Person>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Person person01 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");
        Person person02 = new Person("B", "B", "A St", "City B", "0223", "222-222-2222", "");
        Person person03 = new Person("C", "C", "A St", "City C", "0334", "333-333-3333", "");
        persons.add(person01);
        persons.add(person02);
        persons.add(person03);

        MedicalRecord medicalRecord01 = new MedicalRecord("A", "A", "01/01/2000", null, null);
        MedicalRecord medicalRecord02 = new MedicalRecord("B", "B", "01/01/2010", null, null);
        MedicalRecord medicalRecord03 = new MedicalRecord("C", "C", "01/01/2000", null, null);
        MedicalRecord medicalRecord04 = new MedicalRecord("D", "D", "01/01/2010", null, null);

        medicalRecords.add(medicalRecord01);
        medicalRecords.add(medicalRecord02);
        medicalRecords.add(medicalRecord03);
        medicalRecords.add(medicalRecord04);

        when(medicalRecordService.getAllMedicalRecord()).thenReturn(medicalRecords);

    }

    @Test
    void testGetMedicalRecordByPerson() {
        Person person1 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");

        MedicalRecord result = personMedicalRecordService.getMedicalRecordByPerson(person1);

        assertEquals(medicalRecords.get(0), result);
    }

    @Test
    void testGetChildByAddress() {

        when(personService.getPersonsByAddress("A St")).thenReturn(persons);
        when(utilsService.calculateAge("01/01/2010")).thenReturn(15);
        when(utilsService.calculateAge("01/01/2000")).thenReturn(25);

        AllChildByAddressDTO result = personMedicalRecordService.getChildByAddress("A St");

        assertEquals(1, result.getListChildByAddressDTO().size());
        assertEquals(2, result.getHouseMember().size());
    }

    @Test
    void testGetPersonsByAddressDTO() {

        when(personService.getPersonsByAddress("A St")).thenReturn(persons);

        List<PersonsDTO> result = personMedicalRecordService.getPersonsByAddressDTO("A St");

        assertEquals(3, result.size());
    }

    @Test
    void testGetPersonsDTOByLastName() {

        when(personService.getAllPersons()).thenReturn(persons);
        when(medicalRecordService.getAllMedicalRecord()).thenReturn(medicalRecords);
        when(utilsService.calculateAge("01/01/2000")).thenReturn(22);

        List<PersonsDTO> result = personMedicalRecordService.getPersonsDTOByLastName("A");

        assertEquals(1, result.size());
    }
}