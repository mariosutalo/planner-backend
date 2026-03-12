package app.planner.endpoint.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import app.planner.config.CurrentUser;
import app.planner.endpoint.service.type.CreateServiceRequest;
import app.planner.endpoint.service.type.ServiceCreatedResponse;
import app.planner.endpoint.service.type.ServiceSearchForTableRequest;
import app.planner.endpoint.service.type.ServiceTableResponse;
import app.planner.sharedtypes.PaginatedResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;
    private final CurrentUser currentUser;

    // @PreAuthorize("hasRole('role_user')")
    @PostMapping()
    public ResponseEntity<ServiceCreatedResponse> addNewService(@Valid @RequestBody CreateServiceRequest request) {
        var userUUID = currentUser.getUserId();
        var serviceResponse = service.addNewService(request, userUUID);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceResponse.serviceId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(serviceResponse);
    }

    @GetMapping("/by-user-id")
    public PaginatedResponse<List<ServiceTableResponse>> getServicesByUserId(
            @Valid ServiceSearchForTableRequest request) {
        return service.findSpotsByOwner(request, currentUser.getUserId());

    }
}
