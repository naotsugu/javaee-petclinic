package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.converter.Message;
import code.javaee.sample.petclinic.visit.Visit;
import code.javaee.sample.petclinic.visit.VisitRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.*;

@Path("owners/{ownerId}/pets/{petId}/visits")
@Controller
@RequestScoped
public class VisitController {

    @EJB
    private VisitRepository visits;

    @EJB
    private PetRepository pets;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private Models models;


    @GET
    @Path("new")
    public String initNewVisitForm(@PathParam("petId") int petId) {
        Pet pet = this.pets.findById(petId);
        Visit visit = new Visit();
        pet.addVisit(visit);
        models.put("pet", pet);
        models.put("visit", visit);
        return "pets/createOrUpdateVisitForm.html";
    }

    @POST
    @Path("new")
    public String processNewVisitForm(@PathParam("petId") int petId, @PathParam("ownerId") int ownerId, @Valid @BeanParam Visit visit) {
        if (bindingResult.isFailed()) {
            models.put("message", Message.of(bindingResult));
            Pet pet = this.pets.findById(petId);
            pet.addVisit(visit);
            models.put("pet", pet);
            models.put("visit", visit);
            return "pets/createOrUpdateVisitForm.html";
        } else {
            this.visits.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }

}
