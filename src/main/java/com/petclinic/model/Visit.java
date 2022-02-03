package com.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Visit extends Entity {

    private Long id;
    private String date;
    private String description;
    @NonNull
    private Owner owner;
    @NonNull
    private PetType type;
    @NonNull
    private Pet pet;

}
