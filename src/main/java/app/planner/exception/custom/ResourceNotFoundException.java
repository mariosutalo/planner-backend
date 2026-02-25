package app.planner.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Resource not found, id:" + id);
    }

}
