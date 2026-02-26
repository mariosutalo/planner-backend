package app.planner.endpoint.serviceproperty;

import java.util.List;

public class ServicePropertyValidationException extends RuntimeException {
    private final List<String> errors;

    public ServicePropertyValidationException(List<String> errors) {
        super("Property validation failed");
        this.errors = errors;
    }

    public List<String> getErrors() { return errors; }
}
