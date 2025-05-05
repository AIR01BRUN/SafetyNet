package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.safetynet.model.Person;

import com.example.safetynet.repository.PersonRepositoty;

/**
 * Service class for managing Person entities.
 */
@Service
public class PersonService {

    /**
     * Repository for accessing Person data.
     */

    private final PersonRepositoty personRepository;
    private List<Person> listPersons;

    public PersonService(PersonRepositoty personRepository) {
        this.personRepository = personRepository;
        this.listPersons = personRepository.findAll();
    }

    /**
     * Retrieves all persons.
     * 
     * @return a list of all persons.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieves persons by address.
     * 
     * @param address the address to filter persons by.
     * @return a list of persons living at the specified address.
     */
    public List<Person> getPersonsByAddress(String address) {
        return listPersons.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves email addresses of persons living in a specified city.
     * 
     * @param city the city to filter persons by.
     * @return a list of email addresses of persons living in the specified city.
     */
    public List<String> getEmailByCity(String city) {
        return listPersons.stream()
                .filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new person.
     * 
     * @param person the person to add.
     */
    public void addPerson(Person person) {
        listPersons.add(person);
        updateRepository();
    }

    /**
     * Deletes a person by first and last name.
     * 
     * @param firstName the first name of the person to delete.
     * @param lastName  the last name of the person to delete.
     * @return true if the person was deleted, false otherwise.
     */
    public boolean deletePerson(String firstName, String lastName) {
        Optional<Person> personToDelete = listPersons.stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .findFirst();

        if (personToDelete.isPresent()) {
            listPersons.remove(personToDelete.get());
            updateRepository();
            return true;
        }

        return false;
    }

    /**
     * Updates an existing person.
     * 
     * @param person the person to update.
     * @return true if the person was updated, false otherwise.
     */
    public boolean updatePerson(Person person) {
        Optional<Person> personToUpdate = listPersons.stream()
                .filter(p -> p.getFirstName().equals(person.getFirstName())
                        && p.getLastName().equals(person.getLastName()))
                .findFirst();

        if (personToUpdate.isPresent()) {
            listPersons.set(listPersons.indexOf(personToUpdate.get()), person);
            updateRepository();
            return true;
        }

        return false;
    }

    private void updateRepository() {
        personRepository.update(listPersons);
        this.listPersons = personRepository.findAll();
    }

}
