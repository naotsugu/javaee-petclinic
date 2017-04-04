package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.util.Dates;
import code.javaee.sample.petclinic.visit.Visit;
import code.javaee.sample.petclinic.model.NamedEntity;
import code.javaee.sample.petclinic.core.util.ImmutableListCollector;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import java.util.*;

import static java.util.Comparator.comparing;

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {


    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date birthDate;

    @FormParam("type")
    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotNull
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="pet_id", referencedColumnName="id")
    private Set<Visit> visits = new LinkedHashSet<>();


    @FormParam("birthDateString")
    public void setBirthDateString(String birthDateString) {
        try {
            // JERSEY-2572 workaround
            birthDate = Dates.toDate(birthDateString);
        } catch (Exception e) {
            throw new RuntimeException("date parse error.", e);
        }
    }

    public String getBirthDateString() {
        return Dates.toString(birthDate);
    }

    @NotEmpty
    public String getName() {
        return super.getName();
    }

    @PathParam("petId")
    public void setPetId(int petId) {
        super.setId(petId);
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public PetType getType() {
        return this.type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return this.owner;
    }

    protected void setOwner(Owner owner) {
        this.owner = owner;
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        return getVisitsInternal().stream().sorted(comparing(Visit::getDate))
                .collect(ImmutableListCollector.toImmutableList());
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPetId(this.getId());
    }

}
