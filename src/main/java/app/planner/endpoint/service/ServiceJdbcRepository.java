package app.planner.endpoint.service;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import app.planner.endpoint.service.type.ServiceTableResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ServiceJdbcRepository {

    private final JdbcClient jdbcClient;

    public List<ServiceTableResponse> getServiceForOwner(UUID ownerId, int limit, int offset) {
        var sql = """
                SELECT
                id,
                name,
                FROM service
                WHERE owner_id = :ownerId
                order by name asc
                limit :limit offset :offset;
                        """;
        return jdbcClient.sql(sql)
                .param("ownerId", ownerId)
                .param("limit", limit)
                .param("offset", offset)
                .query(ServiceTableResponse.class)
                .list();
    }

    public Integer countServiceForOwner(UUID ownerId) {
        var sql = """
                select count(*)
                FROM service
                WHERE owner_id = :ownerId;
                """;
        return jdbcClient.sql(sql)
                .param("ownerId", ownerId)
                .query(Integer.class)
                .single();
    }

}
