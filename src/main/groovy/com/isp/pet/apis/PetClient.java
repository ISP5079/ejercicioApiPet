package com.isp.pet.apis;

import com.isp.pet.apis.petrqrp.ApiPetRqRp;
import com.isp.pet.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "petClient", url = "${pet.url.api.external}", configuration = FeignConfig.class)
public interface PetClient {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiPetRqRp getPet(@PathVariable("id") Long petId);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ApiPetRqRp postPet(@RequestBody ApiPetRqRp apiPetRq);
}
