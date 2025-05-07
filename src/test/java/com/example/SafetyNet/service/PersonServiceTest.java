package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepositoty;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class PersonServiceTest {

    @Mock
    private PersonRepositoty personRepository;

    private PersonService personService;

    private List<Person> persons;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Person person1 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");
        Person person2 = new Person("B", "B", "B St", "City B", "1223", "222-222-2222", "BB@email.com");
        Person person3 = new Person("C", "C", "C St", "City A", "1334", "333-333-3333", "CC@email.com");
        persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        personService = new PersonService(personRepository, persons);
    }

    @Test
    void testGetAllPersons() {
        List<Person> result = personService.getAllPersons();
        assertEquals(3, result.size());
    }

    @Test
    void testAddPerson() {
        Person newPerson = new Person("D", "D", "D St", "City D", "1445", "444-444-4444", "DD@email.com");
        personService.addPerson(newPerson);

        verify(personRepository).update(persons);
        assertEquals(4, persons.size());
    }

    @Test
    void testDeletePerson() {
        boolean result = personService.deletePerson("A", "A");

        verify(personRepository).update(persons);
        assertTrue(result);
        assertEquals(2, persons.size());
    }

    @Test
    void testUpdatePerson() {
        Person updatedPerson = new Person("A", "A", "Updated St", "City A", "0112", "111-111-1111", "AA@email.com");
        boolean result = personService.updatePerson(updatedPerson);

        verify(personRepository).update(persons);
        assertTrue(result);
        assertEquals("Updated St", persons.get(0).getAddress());
    }

    @Test
    void testGetPersonsByAddress() {
        List<Person> result = personService.getPersonsByAddress("A St");

        assertEquals(1, result.size());
        assertEquals("A", result.get(0).getFirstName());
    }

    @Test
    void testGetEmailByCity() {
        List<String> emails = personService.getEmailByCity("City A");

        assertEquals(2, emails.size());
        assertEquals("AA@email.com", emails.get(0));
    }

}
