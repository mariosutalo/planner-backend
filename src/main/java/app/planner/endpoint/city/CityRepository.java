package app.planner.endpoint.city;

import org.springframework.data.jpa.repository.JpaRepository;

import app.planner.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
