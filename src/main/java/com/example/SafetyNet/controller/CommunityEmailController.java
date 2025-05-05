package com.example.safetynet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.service.PersonService;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private final PersonService personService;

    @Autowired
    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<String> getEmailsByCity(@RequestParam String city) {
        return personService.getEmailByCity(city);
    }
}
