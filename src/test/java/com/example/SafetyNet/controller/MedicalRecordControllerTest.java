package com.example.safetynet.controller;

import com.example.safetynet.controller.MedicalRecordController;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.MedicalRecordService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Test
    void testAddMedicalRecord() {
        MedicalRecord record = new MedicalRecord("Alice", "Smith", null, null, null);

        ResponseEntity<String> response = medicalRecordController.addMedicalRecord(record);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("MedicalRecord added", response.getBody());
        Mockito.verify(medicalRecordService).addMedicalRecord(record);
    }

    @Test
    void testUpdateMedicalRecordSuccess() {
        MedicalRecord record = new MedicalRecord("Bob", "Johnson", null, null, null);
        Mockito.when(medicalRecordService.updateMedicalRecord(record)).thenReturn(true);

        ResponseEntity<String> response = medicalRecordController.updateMedicalRecord(record);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("updated"));
    }

    @Test
    void testUpdateMedicalRecordNotFound() {
        MedicalRecord record = new MedicalRecord("Unknown", "Person", null, null, null);
        Mockito.when(medicalRecordService.updateMedicalRecord(record)).thenReturn(false);

        ResponseEntity<String> response = medicalRecordController.updateMedicalRecord(record);

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("MedicalRecord not found", response.getBody());
    }

    @Test
    void testDeleteMedicalRecordSuccess() {
        Mockito.when(medicalRecordService.deleteMedicalRecordByName("Alice", "Smith")).thenReturn(true);

        ResponseEntity<String> response = medicalRecordController.deleteMedicalRecord("Alice", "Smith");

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("deleted"));
    }

    @Test
    void testDeleteMedicalRecordNotFound() {
        Mockito.when(medicalRecordService.deleteMedicalRecordByName("Ghost", "User")).thenReturn(false);

        ResponseEntity<String> response = medicalRecordController.deleteMedicalRecord("Ghost", "User");

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("MedicalRecord not found", response.getBody());
    }
}
