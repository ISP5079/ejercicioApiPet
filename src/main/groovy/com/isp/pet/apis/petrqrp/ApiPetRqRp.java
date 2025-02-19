package com.isp.pet.apis.petrqrp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiPetRqRp {
    private Long id;
    private String name;
    private String status;
    private CatApiPet category;
    private List<String> photoUrls;
    private List<CatApiPet> tags;
}
