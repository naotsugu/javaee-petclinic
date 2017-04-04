package code.javaee.sample.petclinic.core.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Dates {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Date toDate(String dateString) {

        if (dateString == null || dateString.trim().length() == 0) {
            return null;
        }

        LocalDate localDate = LocalDate.parse(dateString, formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static String toString(Date date) {
        return (date == null)
                ? ""
                : ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(formatter);
    }

}
