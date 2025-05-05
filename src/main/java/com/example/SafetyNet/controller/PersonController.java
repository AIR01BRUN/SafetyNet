package com.example.safetynet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public String addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return "added";
    }

    @PutMapping
    public String updatePerson(@RequestBody Person person) {
        if (personService.updatePerson(person)) {
            return "updatePersone";
        }
        return "//";
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public String deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deletePerson(firstName, lastName);
        return "deleted";
    }

}
