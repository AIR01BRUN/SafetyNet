package com.example.safetynet.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.safetynet.model.Person;
import com.example.safetynet.util.JsonComponent;

@Repository
public class PersonRepositoty {

    private final JsonComponent jsonComponent;

    public PersonRepositoty(JsonComponent jsonComponent) {
        this.jsonComponent = jsonComponent;
    };

    /**
     * Retrieves all Person objects from the data file.
     *
     * This method reads the JSON data file, parses the "persons" node, and converts
     * it into a list of Person objects.
     * If an error occurs during the reading or parsing process, the exception stack
     * trace is printed.
     *
     * @return a list of Person objects, or an empty list if an error occurs
     */
    public List<Person> findAll() {

        List<Person> listPersons = jsonComponent.getFromJson(Person.class, "persons");

        return listPersons;
    }

    /**
     * Updates the list of persons in the data file.
     *
     * @param listPersons the list of persons to be updated in the data file
     */
    public void update(List<Person> listPersons) {
        jsonComponent.updateJson("persons", listPersons);
    }

}
