package app.planner.endpoint.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/services")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    /* @GetMapping()
    public ResponseEntity<String> getFeaturedServices() {
        var result = service.testCall();
        if (result != null) {
            return ResponseEntity.ok(result.concat("d"));
        }
        return ResponseEntity.notFound().build();
    } */

    

}
