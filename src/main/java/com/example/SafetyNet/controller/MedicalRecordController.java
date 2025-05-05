package com.example.safetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.addMedicalRecord(medicalRecord);
        return "added";
    }

    @PutMapping
    public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        if (medicalRecordService.updateMedicalRecord(medicalRecord)) {
            return "update";
        }
        return "//";
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {

        if (medicalRecordService.deleteMedicalRecordByName(firstName, lastName)) {
            return "deleted";
        }
        return "//";
    }

}
