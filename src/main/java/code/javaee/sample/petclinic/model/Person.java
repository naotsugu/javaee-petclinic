package code.javaee.sample.petclinic.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.ws.rs.FormParam;

@MappedSuperclass
public class Person extends BaseEntity {

    @FormParam("firstName")
    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @FormParam("lastName")
    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
