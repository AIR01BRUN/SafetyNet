package com.example.safetynet.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.util.JsonComponent;

@Repository
public class MedicalRecordRepository {

    private final JsonComponent jsonComponent;

    public MedicalRecordRepository(JsonComponent jsonComponent) {
        this.jsonComponent = jsonComponent;
    };

    /**
     * Retrieves all medical records from the data file.
     *
     * @return a list of all medical records.
     */
    public List<MedicalRecord> findAll() {
        List<MedicalRecord> listMedicalRecords = jsonComponent.getFromJson(MedicalRecord.class, "medicalrecords");

        return listMedicalRecords;
    }

    /**
     * Updates the list of medical records in the data file.
     *
     * @param listMedicalRecords the list of medical records to be updated
     */
    public void update(List<MedicalRecord> listMedicalRecords) {
        jsonComponent.updateJson("medicalrecords", listMedicalRecords);
    }

}
