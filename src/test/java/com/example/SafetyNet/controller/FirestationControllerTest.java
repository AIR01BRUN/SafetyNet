package com.example.safetynet.controller;

import com.example.safetynet.controller.FirestationController;
import com.example.safetynet.DTO.PersonsByStationNumberDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.PersonFirestionMedicalService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FirestationControllerTest {

    @Mock
    private FirestationService firestationService;

    @Mock
    private PersonFirestionMedicalService personFirestionMedicalService;

    @InjectMocks
    private FirestationController firestationController;

    @Test
    void testAddFirestation() {
        Firestation f = new Firestation("123 Main St", 1);

        ResponseEntity<String> response = firestationController.addFirestation(f);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("added"));
        Mockito.verify(firestationService).addFirestation(f);
    }

    @Test
    void testGetPersonsByStationNumberAndAdulteChildByAge() {
        int stationNumber = 3;
        PersonsByStationNumberDTO dto = new PersonsByStationNumberDTO();
        Mockito.when(personFirestionMedicalService.getPersonsByStationNumberAndAdulteChildByAge(stationNumber))
                .thenReturn(dto);

        PersonsByStationNumberDTO result = firestationController
                .getPersonsByStationNumberAndAdulteChildByAge(stationNumber);

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testUpdateFirestationSuccess() {
        Firestation f = new Firestation("456 Oak Ave", 2);
        Mockito.when(firestationService.updateFirestation(f)).thenReturn(true);

        ResponseEntity<String> response = firestationController.updateFirestation(f);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("updated"));
    }

    @Test
    void testUpdateFirestationNotFound() {
        Firestation f = new Firestation("789 Pine Rd", 3);
        Mockito.when(firestationService.updateFirestation(f)).thenReturn(false);

        ResponseEntity<String> response = firestationController.updateFirestation(f);

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("Firestation not found", response.getBody());
    }

    @Test
    void testDeleteFirestationByAddressSuccess() {
        String address = "123 Main St";
        Mockito.when(firestationService.deleteFirestationByAddress(address)).thenReturn(true);

        ResponseEntity<String> response = firestationController.deletePerson(address);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("deleted"));
    }

    @Test
    void testDeleteFirestationByAddressNotFound() {
        String address = "Unknown Address";
        Mockito.when(firestationService.deleteFirestationByAddress(address)).thenReturn(false);

        ResponseEntity<String> response = firestationController.deletePerson(address);

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("Firestation not found", response.getBody());
    }

    @Test
    void testDeleteFirestationByStationSuccess() {
        int station = 5;
        Mockito.when(firestationService.deleteFirestationByStation(station)).thenReturn(true);

        ResponseEntity<String> response = firestationController.deletePerson(station);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("deleted"));
    }

    @Test
    void testDeleteFirestationByStationNotFound() {
        int station = 99;
        Mockito.when(firestationService.deleteFirestationByStation(station)).thenReturn(false);

        ResponseEntity<String> response = firestationController.deletePerson(station);

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("Firestation not found", response.getBody());
    }
}
