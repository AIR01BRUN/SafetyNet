package com.example.safetynet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.service.PersonMedicalRecordService;

@RestController
@RequestMapping()
public class PersonInfoLastName {

    private PersonMedicalRecordService personMedicalRecordService;

    public PersonInfoLastName(PersonMedicalRecordService personMedicalRecordService) {
        this.personMedicalRecordService = personMedicalRecordService;
    }

    @GetMapping("/personInfolastName={lastName}")
    public List<PersonsDTO> getPersonsDTOByLastName(@PathVariable String lastName) {
        return personMedicalRecordService.getPersonsDTOByLastName(lastName);
    }

}
