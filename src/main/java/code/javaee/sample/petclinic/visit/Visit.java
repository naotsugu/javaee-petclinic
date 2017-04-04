package code.javaee.sample.petclinic.visit;

import code.javaee.sample.petclinic.core.util.Dates;
import code.javaee.sample.petclinic.model.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.util.Date;

import static code.javaee.sample.petclinic.visit.Visit.findByPetId;

@Entity
@Table(name = "visits")
@NamedQueries({
    @NamedQuery(name = findByPetId, query = "SELECT visit FROM Visit visit WHERE visit.petId = :petId")
})
public class Visit extends BaseEntity {

    public static final String findByPetId = "Visit.findByPetId";

    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @FormParam("description")
    @NotEmpty
    @Column(name = "description")
    private String description;

    @FormParam("petId")
    @Column(name = "pet_id")
    private Integer petId;


    public Visit() {
        this.date = new Date();
    }

    @FormParam("dateString")
    public void setDateString(String dateString) {
        try {
            date = Dates.toDate(dateString);
        } catch (Exception e) {
            throw new RuntimeException("date parse error.", e);
        }
    }

    public String getDateString() {
        return Dates.toString(date);
    }


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPetId() {
        return this.petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

}
