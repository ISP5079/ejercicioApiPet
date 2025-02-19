package com.isp.pet.controller.rp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isp.pet.controller.rq.PetRq;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetRp extends PetRq {
    private UUID transactionId;
    private Instant dateCreated;

    private String message; //Se utiliza en caso de error.
}
