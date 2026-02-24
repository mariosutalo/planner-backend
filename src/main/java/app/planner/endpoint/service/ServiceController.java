package app.planner.endpoint.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/services")
public class ServiceController {


    @GetMapping()
    public String getFeaturedServices() {
        return "Featured services";
    }
    

}
