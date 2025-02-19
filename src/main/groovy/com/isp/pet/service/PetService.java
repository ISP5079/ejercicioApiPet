package com.isp.pet.service;

import com.isp.pet.apis.PetClient;
import com.isp.pet.apis.petRqRp.ApiPetRqRp;
import com.isp.pet.controller.rp.PetRp;
import com.isp.pet.controller.rq.PetRq;
import com.isp.pet.utils.Constants;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class PetService {

    private final PetClient petClient;

    public PetService(PetClient petClient) {
        this.petClient = petClient;
    }

    public ResponseEntity<PetRp> getPet (Long idPet){
        PetRp rp = new PetRp();
        try {
            ApiPetRqRp apiPetRp = petClient.getPet(idPet);
            log.info(String.format(Constants.FORMAT_PRINT_REQUEST_RESPONSE, Constants.RESPONSE, Constants.GSON.toJson(apiPetRp)));
            rp.setId(apiPetRp.getId());
            rp.setName(apiPetRp.getName());
            rp.setStatus(apiPetRp.getStatus());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(rp);
        } catch (FeignException ex){
            log.error(ex.getMessage());
            rp.setMessage(HttpStatus.resolve(ex.status()).getReasonPhrase());
            return ResponseEntity
                    .status(ex.status())
                    .body(rp);
        }
    }

    public ResponseEntity<PetRp> postPet(PetRq petRq) {
        PetRp rp = new PetRp();
        try {
            ApiPetRqRp apiPetRp = petClient.postPet(Constants.MODEL_MAPPER.map(petRq, ApiPetRqRp.class));
            log.info(String.format(Constants.FORMAT_PRINT_REQUEST_RESPONSE, Constants.RESPONSE, Constants.GSON.toJson(apiPetRp)));
            rp.setTransactionId(UUID.randomUUID());
            rp.setDateCreated(Instant.now());
            rp.setName(apiPetRp.getName());
            rp.setStatus(apiPetRp.getStatus());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(rp);
        }catch (FeignException ex){
            log.error(ex.getMessage());
            rp.setMessage(HttpStatus.resolve(ex.status()).getReasonPhrase());
            return ResponseEntity
                    .status(ex.status())
                    .body(rp);
        }
    }
}
