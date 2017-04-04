package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.repository.Repository;
import code.javaee.sample.petclinic.owner.Owner;

import javax.ejb.Local;
import java.util.Collection;

@Local
public interface OwnerRepository extends Repository<Owner, Integer> {

    void save(Owner owner);

    Owner findById(int ownerId);

    Collection<Owner> findByLastName(String lastName);
}
