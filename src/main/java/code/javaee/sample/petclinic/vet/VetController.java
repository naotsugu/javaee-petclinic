package code.javaee.sample.petclinic.vet;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("vets")
@RequestScoped
public class VetController {

    @EJB
    private VetRepository vets;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private Models models;


    @GET
    @Controller
    public String showVetList() {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        models.put("vets", vets);
        return "vets/vetList.html";
    }


    @GET
    @Path("json")
    @Produces({MediaType.APPLICATION_JSON})
    public Vets showResourcesVetList() {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vets.findAll());
        return vets;
    }

    @GET
    @Path("xml")
    @Produces({MediaType.APPLICATION_XML})
    public Vets showResourcesVetListXml() {
        return showResourcesVetList();
    }

}
