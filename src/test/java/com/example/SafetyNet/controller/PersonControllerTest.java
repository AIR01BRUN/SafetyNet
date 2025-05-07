package com.example.safetynet.controller;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    void testGetAllPersons() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("J", "J"));
        Mockito.when(personService.getAllPersons()).thenReturn(list);

        List<Person> result = personController.getAllPersons();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testAddPerson() {
        Person person = new Person("Jane", "Smith");

        ResponseEntity<String> response = personController.addPerson(person);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Person added", response.getBody());
        Mockito.verify(personService).addPerson(person);
    }

    @Test
    void testUpdatePersonSuccess() {
        Person person = new Person("Alice", "Johnson");
        Mockito.when(personService.updatePerson(person)).thenReturn(true);

        ResponseEntity<String> response = personController.updatePerson(person);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Person updated", response.getBody());
    }

    @Test
    void testUpdatePersonNotFound() {
        Person person = new Person("Ghost", "User");
        Mockito.when(personService.updatePerson(person)).thenReturn(false);

        ResponseEntity<String> response = personController.updatePerson(person);

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("Person not found", response.getBody());
    }

    @Test
    void testDeletePersonSuccess() {
        Mockito.when(personService.deletePerson("John", "Doe")).thenReturn(true);

        ResponseEntity<String> response = personController.deletePerson("John", "Doe");

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Person deleted", response.getBody());
    }

    @Test
    void testDeletePersonNotFound() {
        Mockito.when(personService.deletePerson("Jane", "Ghost")).thenReturn(false);

        ResponseEntity<String> response = personController.deletePerson("Jane", "Ghost");

        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals("Person not found", response.getBody());
    }
}
