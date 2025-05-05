package com.example.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UtilsServiceTest {

    private final UtilsService utilsService = new UtilsService();

    @Test
    void testCalculateAge() {
        String birthDate = "01/01/2000";

        int age = utilsService.calculateAge(birthDate);
        assertEquals(25, age);

    }

}
