package com.example.safetynet.controller;

import org.springframework.http.ResponseEntity;
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

    public FirestationController(FirestationService firestationService,
            PersonFirestionMedicalService personFirestionMedicalService) {
        this.firestationService = firestationService;
        this.personFirestionMedicalService = personFirestionMedicalService;
    }

    @PostMapping
    public ResponseEntity<String> addFirestation(@RequestBody Firestation firestation) {
        firestationService.addFirestation(firestation);
        return ResponseEntity.ok("firestation  : " + firestation + "added");
    }

    @GetMapping
    public PersonsByStationNumberDTO getPersonsByStationNumberAndAdulteChildByAge(@RequestParam int stationNumber) {
        return personFirestionMedicalService.getPersonsByStationNumberAndAdulteChildByAge(stationNumber);
    }

    @PutMapping
    public ResponseEntity<String> updateFirestation(@RequestBody Firestation firestation) {

        if (firestationService.updateFirestation(firestation)) {
            return ResponseEntity.ok("Firestation : " + firestation + " updated");
        } else {
            return ResponseEntity.status(404).body("Firestation not found");
        }

    }

    @DeleteMapping("/byAddress/{address}")
    public ResponseEntity<String> deletePerson(@PathVariable String address) {

        if (firestationService.deleteFirestationByAddress(address)) {
            return ResponseEntity.ok("Firestation: " + address + " deleted");
        } else {
            return ResponseEntity.status(404).body("Firestation not found");
        }
    }

    @DeleteMapping("/byStation/{station}")
    public ResponseEntity<String> deletePerson(@PathVariable int station) {

        if (firestationService.deleteFirestationByStation(station)) {
            return ResponseEntity.ok("Firestation: " + station + " deleted");
        } else {
            return ResponseEntity.status(404).body("Firestation not found");
        }
    }

}
