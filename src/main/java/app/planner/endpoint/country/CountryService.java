package app.planner.endpoint.country;

import app.planner.endpoint.country.type.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryJdbcRepository countryJdbcRepository;

    public List<CountryResponse> getCountries() {
        return countryJdbcRepository.getAllCountries();
    }
}
