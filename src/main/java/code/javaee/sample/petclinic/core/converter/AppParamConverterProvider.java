package code.javaee.sample.petclinic.core.converter;

import code.javaee.sample.petclinic.owner.PetRepository;
import code.javaee.sample.petclinic.owner.PetType;

import javax.ejb.EJB;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

@Provider
public class AppParamConverterProvider implements ParamConverterProvider {

    @EJB
    private PetRepository pets;

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType == Date.class) {
            return (ParamConverter<T>) new DateParamConverter();
        } else if (rawType == PetType.class) {
            return (ParamConverter<T>) new PetTypeParamConverter(pets);
        }
        return null;
    }
}
