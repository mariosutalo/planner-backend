package app.planner.endpoint.country;

import org.springframework.data.jpa.repository.JpaRepository;

import app.planner.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
