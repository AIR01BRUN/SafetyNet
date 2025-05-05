package com.example.safetynet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.safetynet.model.Firestation;

import com.example.safetynet.repository.FirestationRepository;

@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;
    private List<Firestation> listFirestations;

    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
        this.listFirestations = firestationRepository.findAll();
    }

    /**
     * Retrieves a list of all firestations.
     *
     * @return a list of Firestation objects.
     */
    public List<Firestation> getAllFirestation() {
        return listFirestations;
    }

    /**
     * Retrieves a Firestation object based on the provided address.
     *
     * @param address the address of the firestation to retrieve
     * @return the Firestation object that matches the provided address, or null if
     *         no match is found
     */
    public Firestation getFirestationByAddress(String address) {

        Firestation firestationFind = null;

        for (Firestation firestation : listFirestations) {
            if (firestation.getAddress().equals(address)) {
                firestationFind = firestation;
            }
        }

        return firestationFind;
    }

    /**
     * Retrieves a Firestation object by its station number.
     *
     * @param station the station number to search for
     * @return the Firestation object that matches the given station number, or null
     *         if no match is found
     */
    public Firestation getFirestationByStation(int station) {
        return listFirestations.stream()
                .filter(firestation -> firestation.getStation() == station)
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new firestation to the list of firestations and updates the
     * repository.
     *
     * @param firestation the Firestation object to be added
     */

    public void addFirestation(Firestation firestation) {

        listFirestations.add(firestation);
        updateRepository();

    }

    /**
     * Deletes a firestation by its address.
     *
     * @param address the address of the firestation to be deleted
     * @return true if the firestation was successfully deleted, false otherwise
     */
    public boolean deleteFirestationByAddress(String address) {
        Optional<Firestation> firestation = listFirestations.stream()
                .filter(f -> f.getAddress().equals(address))
                .findFirst();

        if (firestation.isPresent()) {
            listFirestations.remove(firestation.get());
            updateRepository();
            return true;
        }

        return false;
    }

    /**
     * Updates an existing firestation with the provided firestation details.
     *
     * @param firestation the firestation object containing updated details
     * @return true if the firestation was successfully updated, false otherwise
     */
    public boolean updateFirestation(Firestation firestation) {
        List<Firestation> listFirestations = getAllFirestation();

        Optional<Firestation> firestationToUpdate = listFirestations.stream()
                .filter(f -> f.getAddress().equals(firestation.getAddress()))
                .findFirst();

        if (firestationToUpdate.isPresent()) {
            listFirestations.set(listFirestations.indexOf(firestationToUpdate.get()), firestation);
            updateRepository();
            return true;
        }

        return false;
    }

    /**
     * Deletes a firestation by its station number.
     *
     * @param station the station number of the firestation to be deleted
     * @return true if the firestation was successfully deleted, false otherwise
     */
    public boolean deleteFirestationByStation(int station) {
        Optional<Firestation> firestation = listFirestations.stream()
                .filter(f -> f.getStation() == station)
                .findFirst();

        if (firestation.isPresent()) {
            listFirestations.remove(firestation.get());
            updateRepository();
            return true;
        }

        return false;

    }

    private void updateRepository() {
        firestationRepository.update(listFirestations);
        this.listFirestations = firestationRepository.findAll();
    }

}
