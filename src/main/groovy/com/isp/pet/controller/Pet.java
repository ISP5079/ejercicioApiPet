package com.isp.pet.controller;

import com.isp.pet.controller.rp.PetRp;
import com.isp.pet.controller.rq.PetRq;
import com.isp.pet.service.PetService;
import com.isp.pet.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestController
@RequestMapping("/api/pet")
@Slf4j
public class Pet {

    private final PetService petService;

    public Pet(PetService petService) {
        this.petService = petService;
    }

    /**
     * Recupera información acerca de una mascota mediante su ID.
     *
     * @param petId El identificador único de la mascota a recuperar. Debe ser un número positivo.
     * @return ResponseEntity con objeto PetRp que contiene la información de la mascota o un mensaje de error si la mascota no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PetRp> getPet(@PathVariable("id") @Min(1) Long petId){
        return petService.getPet(petId);
    }

    /**
     * Publica una nueva mascota usando el cuerpo de la solicitud proporcionada.
     *
     * @param petRq El cuerpo de la solicitud que contiene los detalles de la mascota a crear. No debe ser nulo.
     * @return ResponseEntity que contiene el cuerpo de la respuesta con la información de la mascota creada o un mensaje de error si no tuvo éxito.
     */
    @PostMapping
    public ResponseEntity<PetRp> postPet(@RequestBody @Valid PetRq petRq){
        return petService.postPet(petRq);
    }

    /**
     * Maneja las excepciones de validación lanzadas durante la validación del método del controlador.
     *
     * @param ex La excepción de validación que se debe manejar.
     * @return Un ResponseEntity con un estado HTTP 400 (Bad Request) y un objeto PetRp que contiene un mensaje de error.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<PetRp> handleValidationExceptions(Exception ex) {
        log.debug(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(PetRp.builder().message(Constants.MESSAGE_400).build());
    }
}
