package com.example.safetynet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.HomeByStationDTO;
import com.example.safetynet.service.PersonFirestionMedicalService;

@RestController
@RequestMapping("/fire")
public class FireController {

    private final PersonFirestionMedicalService personFirestionMedicalService;

    public FireController(PersonFirestionMedicalService personFirestionMedicalService) {
        this.personFirestionMedicalService = personFirestionMedicalService;
    }

    @GetMapping
    public List<HomeByStationDTO> getPersonByAddressAddress(@RequestParam String address) {
        return personFirestionMedicalService.getPersonByAddressAddress(address);
    }
}
