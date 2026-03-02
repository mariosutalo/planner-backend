package app.planner.endpoint.service;

import org.springframework.data.jpa.repository.JpaRepository;

import app.planner.domain.ServiceS;

public interface ServiceRepository extends JpaRepository<ServiceS, Long> {

}
