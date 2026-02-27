package app.planner.endpoint.serviceproperty;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import app.planner.domain.ServicePropertyDefinition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ServicePropertyDefinitionJdbcRepository {

    private final JdbcClient client;

    public List<ServicePropertyDefinition> findByServiceTypeId(Long serviceId) {
        var query = """
                select *
                from service_property_definition
                where id = :serviceId;
                """;
        return client.sql(query)
                .param("serviceId", serviceId)
                .query(ServicePropertyDefinition.class)
                .list();
    }

}
