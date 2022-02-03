package com.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetType extends Entity {

    private Long id;
    private String name;

}
