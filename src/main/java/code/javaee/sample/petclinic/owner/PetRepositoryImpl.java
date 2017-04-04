package code.javaee.sample.petclinic.owner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PetRepositoryImpl implements PetRepository {

    @PersistenceContext(unitName = "PetClinicPU")
    private EntityManager em;

    @Override
    public List<PetType> findPetTypes() {
        return em.createNamedQuery(PetType.findPetTypes, PetType.class).getResultList();
    }

    @Override
    public Pet findById(Integer id) {
        return em.find(Pet.class, id);
    }

    @Override
    public void save(Pet pet) {
        if (pet.isNew()) {
            em.persist(pet);
            em.flush();em.clear();
        } else {
            em.merge(pet);
        }
    }

}
