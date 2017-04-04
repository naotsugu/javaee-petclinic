package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.util.ImmutableListCollector;
import code.javaee.sample.petclinic.model.Person;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.ws.rs.FormParam;
import java.util.*;

import static code.javaee.sample.petclinic.owner.Owner.findById;
import static code.javaee.sample.petclinic.owner.Owner.findByLastName;
import static java.util.Comparator.comparing;

@Entity
@Table(name = "owners")
@NamedQueries({
    @NamedQuery(name = findByLastName, query = "SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName"),
    @NamedQuery(name = findById, query = "SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
})
public class Owner extends Person {

    public static final String findByLastName = "Owner.findByLastName";
    public static final String findById = "Owner.findById";

    @FormParam("address")
    @Column(name = "address")
    @NotEmpty
    private String address;

    @FormParam("city")
    @Column(name = "city")
    @NotEmpty
    private String city;

    @FormParam("telephone")
    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected Set<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        return this.pets;
    }

    protected void setPetsInternal(Set<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return getPetsInternal().stream().sorted(comparing(Pet::getName))
                .collect(ImmutableListCollector.toImmutableList());
    }

    public void addPet(Pet pet) {
        if (pet.isNew()) {
            getPetsInternal().add(pet);
        }
        pet.setOwner(this);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : getPetsInternal()) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id='" + getId() + '\'' +
                ", new='" + isNew() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets=" + pets +
                '}';
    }
}
