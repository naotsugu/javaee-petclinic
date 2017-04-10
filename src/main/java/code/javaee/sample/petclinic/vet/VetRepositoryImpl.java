package code.javaee.sample.petclinic.vet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class VetRepositoryImpl implements VetRepository {

    @PersistenceContext(unitName = "PetClinicPU")
    private EntityManager em;

    @Override
    public Collection<Vet> findAll() {
        return em.createNamedQuery(Vet.findAll, Vet.class).getResultList();
    }
}
