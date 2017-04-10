package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.repository.Repository;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PetRepository extends Repository<Pet, Integer> {

    List<PetType> findPetTypes();

    Pet findById(Integer id);

    void save(Pet pet);

}
