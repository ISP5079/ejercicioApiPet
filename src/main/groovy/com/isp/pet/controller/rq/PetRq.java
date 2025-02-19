package com.isp.pet.controller.rq;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRq {
    @Min(1)
    private Long id;
    @NotBlank
    private String status;
    @NotBlank
    private String name;
}
