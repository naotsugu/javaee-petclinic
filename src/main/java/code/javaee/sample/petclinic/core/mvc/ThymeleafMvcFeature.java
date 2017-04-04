package code.javaee.sample.petclinic.core.mvc;

import org.glassfish.jersey.server.mvc.MvcFeature;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

@ConstrainedTo(RuntimeType.SERVER)
public final class ThymeleafMvcFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
//        final Configuration config = context.getConfiguration();
//
//        if (!config.isRegistered(ThymeleafTemplateProcessor.class)) {
//            // Template Processor.
//            context.register(ThymeleafTemplateProcessor.class);
//
//            // MvcFeature.
//            if (!config.isRegistered(MvcFeature.class)) {
//                context.register(MvcFeature.class);
//            }
//            return true;
//        }
        return false;
    }
}

