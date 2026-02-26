package app.planner.endpoint.serviceproperty;

import app.planner.domain.ServicePropertyDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicePropertyDefinitionRepository extends JpaRepository<ServicePropertyDefinition, Long> {
    List<ServicePropertyDefinition> findByServiceTypeId(Long serviceTypeId);
}
