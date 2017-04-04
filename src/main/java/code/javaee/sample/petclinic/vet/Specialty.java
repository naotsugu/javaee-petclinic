package code.javaee.sample.petclinic.vet;

import code.javaee.sample.petclinic.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity implements Serializable {

}
