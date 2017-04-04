package code.javaee.sample.petclinic.owner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class OwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext(unitName = "PetClinicPU")
    private EntityManager em;

    @Override
    public void save(Owner owner) {
        if (owner.isNew()) {
            em.persist(owner);
        } else {
            em.merge(owner);
        }
    }

    @Override
    public Owner findById(int ownerId) {
        return em.createNamedQuery(Owner.findById, Owner.class)
                .setParameter("id", ownerId).getSingleResult();
    }

    @Override
    public Collection<Owner> findByLastName(String lastName) {
        return em.createNamedQuery(Owner.findByLastName, Owner.class)
                .setParameter("lastName", lastName + "%").getResultList();
    }
}
