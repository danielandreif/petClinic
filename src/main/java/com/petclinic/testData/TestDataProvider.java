package com.petclinic.testData;

import com.github.javafaker.Faker;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Visit;

import java.text.Format;
import java.text.SimpleDateFormat;

public class TestDataProvider {

    private Faker faker = new Faker();
    private Format formatter = new SimpleDateFormat("yyyy/MM/dd");
    private String birthdate = formatter.format(faker.date().birthday(1, 6));

    public Owner getOwner() {
        var owner = new Owner();
        owner.setFirstName(faker.name().firstName());
        owner.setLastName(faker.name().lastName());
        owner.setAddress(faker.address().fullAddress());
        owner.setCity(faker.address().city());
        owner.setTelephone(faker.number().digits(faker.number().numberBetween(7, 11)));

        return owner;
    }

    public String generateNumberBetween(int min, int max) {
        return faker.number().digits(faker.number().numberBetween(min, max));
    }

    public PetType getType() {
        PetType type = new PetType();
        type.setName(faker.animal().name());

        return type;
    }

    public Pet getPet(Owner owner, PetType type) {
        Pet pet = new Pet();
        pet.setName(faker.harryPotter().character());
        pet.setBirthDate(birthdate);
        pet.setOwner(owner);
        pet.setType(type);

        return pet;
    }

    public Visit getVisit(Owner owner, PetType type, Pet pet) {
        Visit visit = new Visit();
        visit.setDate(birthdate);
        visit.setDescription(faker.hipster().word());
        visit.setOwner(owner);
        visit.setType(type);
        visit.setPet(pet);

        return visit;
    }
}
