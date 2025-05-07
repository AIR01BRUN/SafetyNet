package com.example.safetynet.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.util.JsonComponent;

class JsonComponentTest {

    @Mock
    private JsonComponent jsonComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFromJsonForPersons() {
        List<Person> people = jsonComponent.getFromJson(Person.class, "persons");

        assertNotNull(people);
    }

    @Test
    void testUpdateJsonForPersons() {
        List<Person> listPersons = new ArrayList<>();

        jsonComponent.updateJson("persons", listPersons);
        verify(jsonComponent, times(1)).updateJson("persons", listPersons);
    }

    @Test
    void testGetFromJsonForMedicalRecords() {
        List<MedicalRecord> medicalRecords = jsonComponent.getFromJson(MedicalRecord.class, "medicalrecords");

        assertNotNull(medicalRecords);
    }

    @Test
    void testUpdateJsonForMedicalRecords() {
        List<MedicalRecord> listMedicalRecords = new ArrayList<>();

        jsonComponent.updateJson("medicalrecords", listMedicalRecords);
        verify(jsonComponent, times(1)).updateJson("medicalrecords", listMedicalRecords);
    }

    @Test
    void testGetFromJsonForFireStations() {
        List<Firestation> fireStations = jsonComponent.getFromJson(Firestation.class, "firestations");

        assertNotNull(fireStations);
    }

    @Test
    void testUpdateJsonForFireStations() {
        List<Firestation> listFireStations = new ArrayList<>();

        jsonComponent.updateJson("firestations", listFireStations);
        verify(jsonComponent, times(1)).updateJson("firestations", listFireStations);
    }

}
