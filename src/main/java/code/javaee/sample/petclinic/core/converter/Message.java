package code.javaee.sample.petclinic.core.converter;

import javax.mvc.binding.BindingError;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Message {

    private final Map<String, String> bindingMessages;
    private final Map<String, String> violationMessages;

    private Message(Map<String, String> bindingMessages, Map<String, String> violationMessages) {
        this.bindingMessages = bindingMessages;
        this.violationMessages = violationMessages;
    }

    public static Message of(BindingResult bindingResult) {

        Map<String, String> binding = bindingResult
                .getAllBindingErrors().stream()
                .collect(Collectors.toMap(BindingError::getParamName, BindingError::getMessage));

        Map<String, String> violation = bindingResult
                .getAllViolations().stream()
                .collect(Collectors.toMap(violationPropertyPath, ConstraintViolation::getMessage));

        return new Message(binding, violation);

    }

    static Function<ConstraintViolation, String> violationPropertyPath = violation -> {
        Iterator<Path.Node> iterator = violation.getPropertyPath().iterator();
        String name = "";
        while (iterator.hasNext()) { name = iterator.next().getName(); }
        return name;
    };

    public boolean hasError(String name) {
        return violationMessages.containsKey(name);
    }
    public String error(String name) {
        return violationMessages.get(name);
    }
}
