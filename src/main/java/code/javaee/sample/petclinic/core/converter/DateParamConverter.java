package code.javaee.sample.petclinic.core.converter;

import javax.ws.rs.ext.ParamConverter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateParamConverter implements ParamConverter<Date> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date fromString(String s) {
        return Date.from(ZonedDateTime.parse(s, formatter).toInstant());
    }

    @Override
    public String toString(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                .format(formatter);
    }
}
