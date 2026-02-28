package app.planner.endpoint.servicetype;

import java.util.List;

import org.springframework.stereotype.Service;

import app.planner.endpoint.servicetype.type.ServiceTypeResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeJdbcRepository typeJdbcRepository;

    public List<ServiceTypeResponse> getServiceTypes(String language) {
        return typeJdbcRepository.getServiceTypesLocalized(language);
    }

}
