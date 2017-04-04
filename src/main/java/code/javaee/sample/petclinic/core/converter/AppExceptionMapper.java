package code.javaee.sample.petclinic.core.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class AppExceptionMapper implements ExceptionMapper<Throwable> {

    @Inject
    private Models models;

    @Override
    public Response toResponse(Throwable e) {
        models.put("message", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity("error.html").build();
    }
}
