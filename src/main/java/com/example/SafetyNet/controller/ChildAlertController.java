package com.example.safetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.AllChildByAddressDTO;
import com.example.safetynet.service.PersonMedicalRecordService;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private PersonMedicalRecordService personMedicalRecordService;

    public ChildAlertController(PersonMedicalRecordService personMedicalRecordService) {
        this.personMedicalRecordService = personMedicalRecordService;
    }

    @GetMapping
    public AllChildByAddressDTO getChildByAddress(@RequestParam String address) {
        return personMedicalRecordService.getChildByAddress(address);
    }
}
