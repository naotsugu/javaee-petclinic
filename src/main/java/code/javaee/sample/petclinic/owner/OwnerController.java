package code.javaee.sample.petclinic.owner;


import code.javaee.sample.petclinic.core.converter.Message;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import java.util.Collection;

@Path("owners")
@Controller
@RequestScoped
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm.html";

    @EJB
    private OwnerRepository repository;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private Models models;


    @GET
    @Path("new")
    public String initCreationForm() {
        Owner owner = new Owner();
        models.put("owner", owner);
        models.put("foo", "bar");
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }


    @POST
    @Path("new")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String processCreationForm(@Valid @BeanParam Owner owner) {
        if (bindingResult.isFailed()) {
            models.put("owner", owner);
            models.put("message", Message.of(bindingResult));
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        repository.save(owner);

        return "redirect:/owners/" + owner.getId();
    }


    @GET
    @Path("{ownerId}")
    public String showOwner(@PathParam("ownerId") int ownerId) {
        models.put("owner", repository.findById(ownerId));
        return "owners/ownerDetails.html";
    }


    @GET
    @Path("find")
    public String initFindForm() {
        models.put("owner", new Owner());
        return "owners/findOwners.html";
    }


    @GET
    public String processFindForm(@DefaultValue("") @QueryParam("lastName") String lastName) {

        Collection<Owner> results = repository.findByLastName(lastName);
        if (results.isEmpty()) {
            models.put("messages", new String[]{"not found"});
            models.put("owner", new Owner());
            return "owners/findOwners.html";
        } else if (results.size() == 1) {
            Owner owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            models.put("selections", results);
            return "owners/ownersList.html";
        }
    }


    @GET
    @Path("{ownerId}/edit")
    public String initUpdateOwnerForm(@PathParam("ownerId") int ownerId) {
        Owner owner = repository.findById(ownerId);
        models.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }


    @POST
    @Path("{ownerId}/edit")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String processUpdateOwnerForm(@Valid @BeanParam Owner owner, @PathParam("ownerId") int ownerId) {
        if (bindingResult.isFailed()) {
            models.put("owner", owner);
            models.put("message", Message.of(bindingResult));
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            repository.save(owner);
            return "redirect:/owners/" + ownerId;
        }
    }

}
