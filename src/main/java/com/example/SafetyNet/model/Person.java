package com.example.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a person with various personal details.
 * 
 * <p>
 * This class is used to store and retrieve information about a person,
 * including their first name, last name, address, city, zip code, phone number,
 * and email address. It provides getter and setter methods for each field.
 * 
 * <p>
 * Example usage:
 * 
 * <pre>
 * Person person = new Person("John", "Doe", "123 Main St", "Anytown", "12345", "555-1234", "john.doe@example.com");
 * String firstName = person.getFirstName();
 * person.setLastName("Smith");
 * </pre>
 * 
 * <p>
 * Fields are annotated with
 * {@link com.fasterxml.jackson.annotation.JsonProperty}
 * to specify the JSON property names when serializing and deserializing.
 * 
 * @see com.fasterxml.jackson.annotation.JsonProperty
 */
public class Person {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String city, String zip, String phone,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
