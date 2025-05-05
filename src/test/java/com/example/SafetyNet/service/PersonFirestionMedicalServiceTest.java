package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.DTO.HomeByStationDTO;
import com.example.safetynet.DTO.PersonsByStationNumberDTO;
import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;

class PersonFirestionMedicalServiceTest {

    @InjectMocks
    private PersonFirestionMedicalService personFirestionMedicalService;

    @Mock
    private PersonFirestationService personFirestationService;
    @Mock
    private PersonMedicalRecordService personMedicalRecordService;
    @Mock
    private MedicalRecordService medicalRecordService;
    @Mock
    private FirestationService firestationService;
    @Mock
    private UtilsService utilsService;

    private List<Person> persons = new ArrayList<Person>();
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Person person01 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");
        Person person02 = new Person("B", "B", "B St", "City B", "0223", "222-222-2222", "");

        persons.add(person01);
        persons.add(person02);

        MedicalRecord medicalRecord01 = new MedicalRecord("A", "A", "01/01/2000", null, null);
        MedicalRecord medicalRecord02 = new MedicalRecord("B", "B", "01/01/2010", null, null);
        medicalRecords.add(medicalRecord01);
        medicalRecords.add(medicalRecord02);
    }

    @Test
    void testGetPersonsByStationNumberAndAdulteChildByAge() {
        int stationNumber = 1;

        when(personFirestationService.getPersonsByStationNumber(stationNumber)).thenReturn(persons);
        when(personMedicalRecordService.getMedicalRecordByPerson(persons.get(0))).thenReturn(medicalRecords.get(0));
        when(personMedicalRecordService.getMedicalRecordByPerson(persons.get(1))).thenReturn(medicalRecords.get(1));
        when(utilsService.calculateAge(medicalRecords.get(0).getBirthdate())).thenReturn(23);
        when(utilsService.calculateAge(medicalRecords.get(1).getBirthdate())).thenReturn(13);

        PersonsByStationNumberDTO result = personFirestionMedicalService
                .getPersonsByStationNumberAndAdulteChildByAge(stationNumber);

        assertEquals(1, result.getNumberAdult());
        assertEquals(1, result.getNumberChildren());

    }

    @Test
    void testGetPersonByAddressAddress() {
        String address = "A St";
        Firestation firestation = new Firestation(address, 1);

        Person person01 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");
        List<Person> personsAdd = Collections.singletonList(person01);

        when(firestationService.getFirestationByAddress(address)).thenReturn(firestation);
        when(personFirestationService.getPersonsByStationNumber(firestation.getStation())).thenReturn(personsAdd);
        when(personMedicalRecordService.getPersonsByAddressDTO(address)).thenReturn(Collections
                .singletonList(new PersonsDTO("A", "A", "111-111-1111", 23, null, null)));

        List<HomeByStationDTO> result = personFirestionMedicalService.getPersonByAddressAddress(address);

        assertEquals(1, result.size());
        assertEquals(address, result.get(0).getAddress());

    }

    @Test
    void testGetHouseholdsByStations() {
        List<Integer> stationNumbers = Arrays.asList(1, 2);
        Firestation firestation1 = new Firestation("A St", 1);
        Firestation firestation2 = new Firestation("B St", 2);

        when(firestationService.getFirestationByAddress("A St")).thenReturn(firestation1);
        when(firestationService.getFirestationByAddress("B St")).thenReturn(firestation2);
        when(personFirestationService.getPersonsByStationNumber(1)).thenReturn(persons);
        when(personFirestationService.getPersonsByStationNumber(2)).thenReturn(persons);
        when(firestationService.getFirestationByStation(1)).thenReturn(firestation1);
        when(firestationService.getFirestationByStation(2)).thenReturn(firestation2);
        when(personFirestionMedicalService.getPersonByAddressAddress("A St"))
                .thenReturn(Collections.singletonList(new HomeByStationDTO("A St", 1,
                        Collections.singletonList(new PersonsDTO("A", "A", "111-111-1111", 23, null, null)))));
        when(personFirestionMedicalService.getPersonByAddressAddress("B St"))
                .thenReturn(Collections.singletonList(new HomeByStationDTO("B St", 2,
                        Collections.singletonList(new PersonsDTO("B", "B", "222-222-2222", 13, null, null)))));

        List<HomeByStationDTO> result = personFirestionMedicalService.getHouseholdsByStations(stationNumbers);

        assertEquals(4, result.size());
        assertEquals("A St", result.get(0).getAddress());
        assertEquals("B St", result.get(1).getAddress());
    }

}
