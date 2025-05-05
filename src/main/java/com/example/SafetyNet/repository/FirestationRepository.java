package com.example.safetynet.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.util.JsonComponent;

@Repository
public class FirestationRepository {
    private final JsonComponent jsonComponent;

    public FirestationRepository(JsonComponent jsonComponent) {
        this.jsonComponent = jsonComponent;
    }

    /**
     * Retrieves all firestations from the data file.
     *
     * This method reads the JSON data file and extracts the list of firestations.
     * It uses the ObjectMapper to parse the JSON and convert it into a list of
     * Firestation objects.
     *
     * @return a list of Firestation objects, or an empty list if an error occurs
     *         during reading or parsing the data file.
     */
    public List<Firestation> findAll() {

        List<Firestation> listFirestations = jsonComponent.getFromJson(Firestation.class, "firestations");

        return listFirestations;

    }

    /**
     * Updates the list of firestations in the data file.
     *
     * @param listFirestations the list of firestations to be updated in the data
     *                         file
     */

    public void update(List<Firestation> listFirestations) {
        jsonComponent.updateJson("firestations", listFirestations);
    }

}
