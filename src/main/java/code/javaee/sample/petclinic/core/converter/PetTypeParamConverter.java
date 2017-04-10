package code.javaee.sample.petclinic.core.converter;

import code.javaee.sample.petclinic.owner.PetRepository;
import code.javaee.sample.petclinic.owner.PetType;

import javax.ws.rs.ext.ParamConverter;
import java.util.Collection;

public class PetTypeParamConverter implements ParamConverter<PetType> {

    private final PetRepository pets;

    PetTypeParamConverter(PetRepository pets) {
        this.pets = pets;
    }

    @Override
    public PetType fromString(String text) {
        Collection<PetType> findPetTypes = this.pets.findPetTypes();
        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new RuntimeException("type not found: " + text);
    }

    @Override
    public String toString(PetType petType) {
        return petType.getName();
    }
}
