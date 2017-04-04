package code.javaee.sample.petclinic.vet;

import code.javaee.sample.petclinic.core.util.ImmutableListCollector;
import code.javaee.sample.petclinic.model.Person;
import code.javaee.sample.petclinic.vet.Specialty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

import static code.javaee.sample.petclinic.vet.Vet.findAll;
import static code.javaee.sample.petclinic.visit.Visit.findByPetId;
import static java.util.Comparator.comparing;

@Entity
@Table(name = "vets")
@NamedQueries({
        @NamedQuery(name = findAll, query = "SELECT vet FROM Vet vet")
})
public class Vet extends Person {

    public static final String findAll = "Vet.findAll";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        return sortedSpecs.stream().sorted(comparing(Specialty::getName))
                .collect(ImmutableListCollector.toImmutableList());
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

}
