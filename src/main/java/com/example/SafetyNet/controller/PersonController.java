package com.example.safetynet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personService.addPerson(person);

        return ResponseEntity.ok("Person added");
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        if (personService.updatePerson(person)) {
            return ResponseEntity.ok("Person updated");
        } else {
            return ResponseEntity.status(404).body("Person not found");
        }

    }

    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {

        if (personService.deletePerson(firstName, lastName)) {
            return ResponseEntity.ok("Person deleted");
        } else {
            return ResponseEntity.status(404).body("Person not found");
        }

    }

}
