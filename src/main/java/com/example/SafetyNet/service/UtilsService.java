package com.example.safetynet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    /**
     * Calculates the age based on the given birth date.
     *
     * @param birth the birth date in the format "MM/dd/yyyy"
     * @return the calculated age in years
     */
    public int calculateAge(String birth) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birth, formatter);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

}
