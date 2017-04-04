package code.javaee.sample.petclinic.core.mvc;

/**
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.AbstractTemplateProcessor;
import org.jvnet.hk2.annotations.Optional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
**/

final class ThymeleafTemplateProcessor {
/** extends AbstractTemplateProcessor<String> {

    @Inject
    private Provider<Ref<HttpServletRequest>> requestProviderRef;
    @Inject
    private Provider<Ref<HttpServletResponse>> responseProviderRef;

    private final TemplateEngine templateEngine;

    @Inject
    public ThymeleafTemplateProcessor(Configuration config, @Optional ServletContext servletContext) {

        super(config, servletContext, "html", "html");
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix((String)config.getProperty(MvcFeature.TEMPLATE_BASE_PATH));
        templateResolver.setSuffix(".html");
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

    }


    @Override
    protected String resolve(String templatePath, Reader reader) throws Exception {
        return templatePath;
    }


    @Override
    public void writeTo(String templateReference, Viewable viewable, MediaType mediaType,
                        MultivaluedMap<String, Object> multivaluedMap,
                        OutputStream outputStream) throws IOException {

        HttpServletRequest request = requestProviderRef.get().get();
        HttpServletResponse response = responseProviderRef.get().get();


        WebContext webContext = new WebContext(
                request, response, super.getServletContext(), request.getLocale());

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (viewable.getModel() instanceof Map)
                ? (Map<String, Object>) viewable.getModel()
                : new HashMap<String, Object>() {{
                    put("model", viewable.getModel());
                  }};
        webContext.setVariables(map);

        templateEngine.process(viewable.getTemplateName(), webContext,
                new OutputStreamWriter(outputStream));
    }
*/
}
