package app.planner.endpoint.servicetype;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.planner.endpoint.servicetype.type.ServiceTypeResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-types")
@RequiredArgsConstructor
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;


    @GetMapping("/all")
    public List<ServiceTypeResponse> getAllServiceTypes(String langCode) {
        return serviceTypeService.getServiceTypes(langCode);
    }

}
