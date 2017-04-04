package code.javaee.sample.petclinic.model;

import code.javaee.sample.petclinic.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.ws.rs.FormParam;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @FormParam("name")
    @Column(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
