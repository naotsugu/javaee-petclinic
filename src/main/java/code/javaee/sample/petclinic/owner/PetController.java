package code.javaee.sample.petclinic.owner;

import code.javaee.sample.petclinic.core.converter.Message;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.Date;

@Path("owners/{ownerId}/pets")
@Controller
@RequestScoped
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm.html";

    @EJB
    private PetRepository pets;

    @EJB
    private OwnerRepository owners;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private Models models;


    @GET
    @Path("new")
    public String initCreationForm(@PathParam("ownerId") int ownerId) {
        Owner owner = owners.findById(ownerId);
        Pet pet = new Pet();
        owner.addPet(pet);
        initModels(pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @POST
    @Path("new")
    public String processCreationForm(@PathParam("ownerId") int ownerId, @Valid @BeanParam Pet pet) {

        if (bindingResult.isFailed()) {
            models.put("message", Message.of(bindingResult));
            initModels(pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        Owner owner = this.owners.findById(ownerId);
        if (pet.getName() != null &&
                pet.getName().trim().length() > 0 &&
                pet.isNew() &&
                owner.getPet(pet.getName(), true) != null){
            models.put("messages", "name duplicate already exists");
            initModels(pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.addPet(pet);
            this.pets.save(pet);
            return "redirect:/owners/" + ownerId;
        }
    }


    @GET
    @Path("{petId}/edit")
    public String initUpdateForm(@PathParam("petId") int petId) {
        Pet pet = this.pets.findById(petId);
        initModels(pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @POST
    @Path("{petId}/edit")
    public String processUpdateForm(@PathParam("ownerId") int ownerId, @BeanParam Pet pet) {
        if (bindingResult.isFailed()) {
            models.put("message", Message.of(bindingResult));
            pet.setOwner(owners.findById(ownerId));
            initModels(pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            Owner owner = owners.findById(ownerId);
            owner.addPet(pet);
            this.pets.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    private void initModels(Pet pet) {
        models.put("pet", pet);
        models.put("types", pets.findPetTypes());
    }

}
