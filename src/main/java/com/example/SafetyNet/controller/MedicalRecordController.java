package com.example.safetynet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.addMedicalRecord(medicalRecord);
        return ResponseEntity.ok("MedicalRecord added");
    }

    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        if (medicalRecordService.updateMedicalRecord(medicalRecord)) {
            return ResponseEntity.ok("MedicalRecord: " + medicalRecord + " updated");
        } else {
            return ResponseEntity.status(404).body("MedicalRecord not found");
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {

        if (medicalRecordService.deleteMedicalRecordByName(firstName, lastName)) {
            return ResponseEntity.ok("MedicalRecord: " + firstName + " " + lastName + " deleted");
        } else {
            return ResponseEntity.status(404).body("MedicalRecord not found");
        }
    }

}
