package com.example.safetynet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a medical record of an individual.
 * Contains personal information such as first name, last name, birthdate,
 * medications, and allergies.
 * 
 * <p>
 * This class is used to store and retrieve medical information for a person.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * List<String> medications = Arrays.asList("med1", "med2");
 * List<String> allergies = Arrays.asList("allergy1", "allergy2");
 * MedicalRecord record = new MedicalRecord("John", "Doe", "01/01/2000", medications, allergies);
 * }
 * </pre>
 * 
 * <p>
 * The class provides getter and setter methods for each field.
 * </p>
 * 
 * <p>
 * Annotations:
 * </p>
 * <ul>
 * <li>{@link com.fasterxml.jackson.annotation.JsonProperty} - Used to specify
 * the JSON property names for serialization and deserialization.</li>
 * </ul>
 * 
 * @see com.fasterxml.jackson.annotation.JsonProperty
 */
public class MedicalRecord {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("medications")
    private List<String> medications;

    @JsonProperty("allergies")
    private List<String> allergies;

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications,
            List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

}
