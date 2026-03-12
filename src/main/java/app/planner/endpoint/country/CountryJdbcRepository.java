package app.planner.endpoint.country;

import app.planner.endpoint.country.type.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CountryJdbcRepository {

    private final JdbcClient jdbcClient;

    public List<CountryResponse> getAllCountries() {
        var query = """
                select id, name
                from country;
                """;
        return jdbcClient
                .sql(query)
                .query(CountryResponse.class)
                .list();
    }


}
