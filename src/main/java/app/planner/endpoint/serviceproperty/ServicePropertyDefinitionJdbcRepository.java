package app.planner.endpoint.serviceproperty;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import app.planner.domain.DataType;
import app.planner.domain.ServicePropertyDefinition;
import app.planner.util.PgJsonUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ServicePropertyDefinitionJdbcRepository {

    private final JdbcClient client;

    public List<ServicePropertyDefinition> findByServiceTypeId(Long serviceTypeId) {
        var query = """
                select id,
                service_type_id as serviceTypeId,
                name,
                data_type as dataType,
                required,
                options
                from service_property_definition
                where service_type_id = :serviceTypeId;
                """;
        return client.sql(query)
                .param("serviceTypeId", serviceTypeId)
                .query((rs, rowNum) -> {
                    var spd = new ServicePropertyDefinition();
                    spd.setId(rs.getLong("id"));
                    spd.setName(rs.getString("name"));
                    spd.setDataType(DataType.valueOf(rs.getString("dataType")));
                    spd.setRequired(rs.getBoolean("required"));
                    spd.setOptions(PgJsonUtil.toJsonNode(rs, "options"));
                    return spd;
                })
                .list();
    }

}
