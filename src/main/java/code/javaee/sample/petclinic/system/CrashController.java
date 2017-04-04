package code.javaee.sample.petclinic.system;

import javax.enterprise.context.RequestScoped;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@Controller
public class CrashController {

    @GET
    @Path("oups")
    public String triggerException() {
        throw new RuntimeException(
                "Expected: controller used to showcase what " + "happens when an exception is thrown");
    }
}
