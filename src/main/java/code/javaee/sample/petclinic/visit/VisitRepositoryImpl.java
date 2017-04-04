package code.javaee.sample.petclinic.visit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VisitRepositoryImpl implements VisitRepository {

    @PersistenceContext(unitName = "PetClinicPU")
    private EntityManager em;

    @Override
    public void save(Visit visit) {
        if (visit.isNew()) {
            em.persist(visit);
        } else {
            em.merge(visit);
        }
    }

    @Override
    public List<Visit> findByPetId(Integer petId) {
        return em.createNamedQuery(Visit.findByPetId, Visit.class)
                .setParameter("petId", petId).getResultList();
    }

}
