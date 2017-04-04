package code.javaee.sample.petclinic.visit;

import code.javaee.sample.petclinic.visit.Visit;
import code.javaee.sample.petclinic.core.repository.Repository;

import javax.ejb.Local;
import java.util.List;

@Local
public interface VisitRepository extends Repository<Visit, Integer> {

    void save(Visit visit);

    List<Visit> findByPetId(Integer petId);
}
