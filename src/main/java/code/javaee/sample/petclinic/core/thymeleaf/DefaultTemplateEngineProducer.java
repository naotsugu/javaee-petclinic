package code.javaee.sample.petclinic.core.thymeleaf;

import org.glassfish.ozark.engine.ViewEngineConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.mvc.engine.ViewEngine;
import javax.servlet.ServletContext;

@Dependent
public class DefaultTemplateEngineProducer {

    @Produces
    @ViewEngineConfig
    public TemplateEngine getTemplateEngine(ServletContext context) {

        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix(ViewEngine.DEFAULT_VIEW_FOLDER);
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;

    }
}
