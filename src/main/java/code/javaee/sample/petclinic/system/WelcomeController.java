package code.javaee.sample.petclinic.system;

import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@Controller
public class WelcomeController {

    @GET
    public String welcome() {
        return "welcome.html";
    }

}
