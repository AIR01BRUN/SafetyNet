package com.example.safetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.PersonsByStationNumberDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.PersonFirestionMedicalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private final FirestationService firestationService;

    private PersonFirestionMedicalService personFirestionMedicalService;

    @Autowired
    public FirestationController(FirestationService firestationService,
            PersonFirestionMedicalService personFirestionMedicalService) {
        this.firestationService = firestationService;
        this.personFirestionMedicalService = personFirestionMedicalService;
    }

    @PostMapping
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        firestationService.addFirestation(firestation);
        return firestation;
    }

    @GetMapping
    public PersonsByStationNumberDTO getPersonsByStationNumberAndAdulteChildByAge(@RequestParam int stationNumber) {
        return personFirestionMedicalService.getPersonsByStationNumberAndAdulteChildByAge(stationNumber);
    }

    @PutMapping
    public Firestation updateFirestation(@RequestBody Firestation firestation) {
        firestationService.updateFirestation(firestation);
        return firestation;

    }

    @DeleteMapping("/byAddress/{address}")
    public String deletePerson(@PathVariable String address) {

        if (firestationService.deleteFirestationByAddress(address)) {
            return "deleted by address";
        }
        return "//";
    }

    @DeleteMapping("/byStation/{station}")
    public String deletePerson(@PathVariable int station) {

        if (firestationService.deleteFirestationByStation(station)) {
            return "deleted by address";
        }
        return "//";
    }

}
