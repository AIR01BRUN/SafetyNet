package com.example.safetynet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private List<MedicalRecord> listMedicalRecords;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.listMedicalRecords = medicalRecordRepository.findAll();

    }

    /**
     * Retrieves a list of all medical records.
     *
     * @return a list of MedicalRecord objects
     */
    public List<MedicalRecord> getAllMedicalRecord() {
        return listMedicalRecords;
    }

    /**
     * Adds a new medical record to the list of medical records.
     *
     * @param medicalRecord the medical record to be added
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        listMedicalRecords.add(medicalRecord);
        updateRepository();
    }

    /**
     * Updates an existing medical record in the repository.
     *
     * @param medicalRecord the medical record to update
     * @return true if the medical record was successfully updated, false otherwise
     */
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> existingRecord = listMedicalRecords.stream()
                .filter(record -> record.getFirstName().equals(medicalRecord.getFirstName()) &&
                        record.getLastName().equals(medicalRecord.getLastName()))
                .findFirst();

        if (existingRecord.isPresent()) {
            listMedicalRecords.set(listMedicalRecords.indexOf(existingRecord.get()), medicalRecord);
            updateRepository();
            return true;
        }

        return false;
    }

    /**
     * Deletes a medical record by the given first name and last name.
     *
     * @param firsttName the first name of the medical record to delete
     * @param lastName   the last name of the medical record to delete
     * @return true if the medical record was found and deleted, false otherwise
     */
    public boolean deleteMedicalRecordByName(String firstName, String lastName) {
        boolean deleted = listMedicalRecords
                .removeIf(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName));

        if (deleted) {
            updateRepository(); // Mise à jour de la liste
        }

        return deleted;
    }

    private void updateRepository() {
        medicalRecordRepository.update(listMedicalRecords);
        this.listMedicalRecords = medicalRecordRepository.findAll();
    }
}
