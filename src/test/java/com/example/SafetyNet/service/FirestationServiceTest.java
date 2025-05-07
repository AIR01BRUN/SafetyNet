package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationRepository;

class FirestationServiceTest {

    FirestationService firestationService;

    @Mock
    FirestationRepository firestationRepository;

    private List<Firestation> firestations = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Firestation firestation01 = new Firestation("10 St", 10);
        Firestation firestation02 = new Firestation("15 St", 15);
        firestations.add(firestation01);
        firestations.add(firestation02);

        firestationService = new FirestationService(firestationRepository, firestations);
    }

    @Test
    void testGetAllFirestation() {

        List<Firestation> result = firestationService.getAllFirestation();

        assertEquals(2, result.size());
    }

    @Test
    void testGetFirestationByAddress() {

        Firestation result = firestationService.getFirestationByAddress("10 St");

        assertNotNull(result);
        assertEquals("10 St", result.getAddress());
    }

    @Test
    void testGetFirestationByStation() {

        Firestation result = firestationService.getFirestationByStation(10);

        assertNotNull(result);
        assertEquals(10, result.getStation());
    }

    @Test
    void testAddFirestation() {
        Firestation firestation = new Firestation();

        firestationService.addFirestation(firestation);

        verify(firestationRepository, times(1)).update(anyList());
    }

    @Test
    void testDeleteFirestationByAddress() {

        boolean result = firestationService.deleteFirestationByAddress("10 St");

        assertTrue(result);
        verify(firestationRepository, times(1)).update(anyList());
    }

    @Test
    void testUpdateFirestation() {
        Firestation firestation = new Firestation("10 St", 11);

        boolean result = firestationService.updateFirestation(firestation);

        assertTrue(result);
        verify(firestationRepository, times(1)).update(anyList());
    }

    @Test
    void testDeleteFirestationByStation() {

        boolean result = firestationService.deleteFirestationByStation(10);

        assertTrue(result);
        verify(firestationRepository, times(1)).update(anyList());
    }

}