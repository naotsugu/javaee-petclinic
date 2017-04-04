package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static code.javaee.sample.petclinic.owner.PetType.findPetTypes;

@Entity
@Table(name = "types")
@NamedQueries({
    @NamedQuery(name = findPetTypes, query = "SELECT ptype FROM PetType ptype ORDER BY ptype.name")
})
public class PetType extends NamedEntity {

    public static final String findPetTypes = "Pet.findPetTypes";

}
