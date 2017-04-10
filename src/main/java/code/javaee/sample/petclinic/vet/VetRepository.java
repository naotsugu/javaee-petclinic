package code.javaee.sample.petclinic.vet;

import code.javaee.sample.petclinic.core.repository.Repository;

import javax.ejb.Local;
import java.util.Collection;

@Local
public interface VetRepository extends Repository<Vet, Integer> {

    Collection<Vet> findAll();

}
