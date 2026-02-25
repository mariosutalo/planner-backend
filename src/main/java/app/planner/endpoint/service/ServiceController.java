package app.planner.endpoint.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import app.planner.config.CurrentUser;
import app.planner.endpoint.service.type.CreateServiceRequest;
import app.planner.endpoint.service.type.ServiceCreatedResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/services")
public class ServiceController {

    private final ServiceService service;
    private final CurrentUser currentUser;

    public ServiceController(ServiceService service, CurrentUser user) {
        this.service = service;
        this.currentUser = user;
    }

    @PreAuthorize("hasRole('role_user')")
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

    @GetMapping()
    public ResponseEntity<String> getFeaturedServices() {
        var result = service.testCall();
        if (result != null) {
            return ResponseEntity.ok(result.concat("d"));
        }
        return ResponseEntity.notFound().build();
    }

}
