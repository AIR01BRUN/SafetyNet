package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FirestationRepository;

class PersonFirestationServiceTest {

    @InjectMocks
    private PersonFirestationService personFirestationService;

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private PersonService personService;

    private List<Person> persons = new ArrayList<Person>();
    private List<Firestation> firestations = new ArrayList<Firestation>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Person person01 = new Person("A", "A", "A St", "City A", "0112", "111-111-1111", "AA@email.com");
        Person person02 = new Person("B", "B", "B St", "City B", "0223", "222-222-2222", "");
        Person person03 = new Person("C", "C", "C St", "City C", "0334", "333-333-3333", "");
        persons.add(person01);
        persons.add(person02);
        persons.add(person03);

        Firestation firestation01 = new Firestation("A St", 10);
        Firestation firestation02 = new Firestation("B St", 15);
        firestations.add(firestation01);
        firestations.add(firestation02);

        when(personService.getAllPersons()).thenReturn(persons);
        when(firestationRepository.findAll()).thenReturn(firestations);
    }

    @Test
    void testGetPersonsByStationNumber() {

        List<Person> result = personFirestationService.getPersonsByStationNumber(10);

        assertEquals(1, result.size());
        assertEquals("A St", result.get(0).getAddress());
    }

    @Test
    void testGetPhoneNumber() {
        List<String> result = personFirestationService.getPhoneNumber(10);
        assertEquals(1, result.size());
        assertEquals("111-111-1111", result.get(0));
    }

}