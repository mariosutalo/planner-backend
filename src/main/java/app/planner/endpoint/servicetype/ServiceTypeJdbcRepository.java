package app.planner.endpoint.servicetype;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import app.planner.endpoint.servicetype.type.ServiceTypeResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ServiceTypeJdbcRepository {

    private final JdbcClient jdbcClient;

    public List<ServiceTypeResponse> getServiceTypesLocalized(String langCode) {
        if (langCode == null || langCode.isBlank()) {
            langCode = "en";
        }

        var query = """
                SELECT s.id, sts.name
                FROM service_type s
                JOIN service_type_translation sts ON sts.service_type_id = s.id
                JOIN language l ON sts.language_id = l.id
                WHERE l.code = :langCode
                """;

        return jdbcClient
                .sql(query)
                .param("langCode", langCode)
                .query(ServiceTypeResponse.class)
                .list();
    }

}
