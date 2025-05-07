package com.example.safetynet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.HomeByStationDTO;
import com.example.safetynet.service.PersonFirestionMedicalService;

@RestController
@RequestMapping("/flood")
public class FloodController {

    private final PersonFirestionMedicalService personFirestionMedicalService;

    public FloodController(PersonFirestionMedicalService personFirestionMedicalService) {
        this.personFirestionMedicalService = personFirestionMedicalService;
    }

    @GetMapping("/stations")
    public List<HomeByStationDTO> getHouseholdsByStations(@RequestParam List<Integer> stations) {
        return personFirestionMedicalService.getHouseholdsByStations(stations);
    }

}
