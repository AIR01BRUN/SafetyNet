package com.example.safetynet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.service.PersonFirestationService;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private PersonFirestationService personFirestationService;

    @Autowired
    public PhoneAlertController(PersonFirestationService personFirestationService) {
        this.personFirestationService = personFirestationService;
    }

    @GetMapping
    public List<String> getChildByAddress(@RequestParam int firestation) {
        return personFirestationService.getPhoneNumber(firestation);
    }

}
