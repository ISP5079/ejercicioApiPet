package com.isp.pet.controller;

import com.isp.pet.controller.rp.PetRp;
import com.isp.pet.controller.rq.PetRq;
import com.isp.pet.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PetControllerTest {
    private Pet pet;
    private final Long TEST_PET_ID = 1L;

    @BeforeEach
    void setup() {
        PetService petService = mock(PetService.class);
        pet = new Pet(petService);
    }

    @Test
    void testGetPet() {
        PetRp petRp = new PetRp();
        when(pet.getPet(TEST_PET_ID)).thenReturn(ResponseEntity.ok(petRp));

        ResponseEntity<PetRp> response = pet.getPet(TEST_PET_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(petRp, response.getBody());
    }

    @Test
    void testPostPet() {
        PetRq petRq = new PetRq();
        PetRp petRp = new PetRp();
        when(pet.postPet(petRq)).thenReturn(ResponseEntity.ok(petRp));

        ResponseEntity<PetRp> response = pet.postPet(petRq);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(petRp, response.getBody());
    }
}