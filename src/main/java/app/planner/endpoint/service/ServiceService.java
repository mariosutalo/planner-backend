package app.planner.endpoint.service;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import app.planner.endpoint.service.type.CreateServiceRequest;
import app.planner.endpoint.service.type.ServiceCreatedResponse;

@Service
public class ServiceService {

    public @Nullable String testCall() {
        return null;
    }

    public ServiceCreatedResponse addNewService(CreateServiceRequest request, @Nullable String userUUID) {
        if(userUUID == null) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "User UUID is null"
            );
        }
        // to do
        return new ServiceCreatedResponse(1L);
    }

}
