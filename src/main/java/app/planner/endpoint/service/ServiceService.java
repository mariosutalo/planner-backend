package app.planner.endpoint.service;

import app.planner.endpoint.serviceproperty.ServicePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import app.planner.endpoint.service.type.CreateServiceRequest;
import app.planner.endpoint.service.type.ServiceCreatedResponse;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServicePropertyValidator servicePropertyValidator;

    public @Nullable String testCall() {
        return null;
    }

    public ServiceCreatedResponse addNewService(CreateServiceRequest request, @Nullable String userUUID) {
//        if(userUUID == null) {
//            throw new ResponseStatusException(
//                HttpStatus.INTERNAL_SERVER_ERROR, "User UUID is null"
//            );
//        }
        servicePropertyValidator.validate(
                request.serviceTypeId(),
                request.properties()
        );
        // to do
        return new ServiceCreatedResponse(1L);
    }

}
