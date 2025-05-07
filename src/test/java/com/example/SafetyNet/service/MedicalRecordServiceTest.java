package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;

class MedicalRecordServiceTest {

    MedicalRecordService medicalRecordService;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        MedicalRecord medicalRecord01 = new MedicalRecord("A", "A", "01/01/2000", null, null);
        MedicalRecord medicalRecord02 = new MedicalRecord("B", "B", "02/02/2000", null, null);
        medicalRecords.add(medicalRecord01);
        medicalRecords.add(medicalRecord02);

        medicalRecordService = new MedicalRecordService(medicalRecordRepository, medicalRecords);
    }

    @Test
    void testGetAllMedicalRecord() {
        List<MedicalRecord> result = medicalRecordService.getAllMedicalRecord();
        assertEquals(2, result.size());
    }

    @Test
    void testAddMedicalRecord() {
        MedicalRecord newRecord = new MedicalRecord("C", "C", "03/03/2000", null, null);
        medicalRecordService.addMedicalRecord(newRecord);

        verify(medicalRecordRepository, times(1)).update(anyList());
        assertEquals(3, medicalRecords.size());
    }

    @Test
    void testUpdateMedicalRecord() {
        MedicalRecord updatedRecord = new MedicalRecord("A", "A", "01/01/2001", null, null);
        boolean result = medicalRecordService.updateMedicalRecord(updatedRecord);

        assertTrue(result);
        verify(medicalRecordRepository, times(1)).update(anyList());
    }

    @Test
    void testDeleteMedicalRecordByName() {
        boolean result = medicalRecordService.deleteMedicalRecordByName("A", "A");

        assertTrue(result);
        verify(medicalRecordRepository, times(1)).update(anyList());
        assertEquals(1, medicalRecords.size());
    }

}
