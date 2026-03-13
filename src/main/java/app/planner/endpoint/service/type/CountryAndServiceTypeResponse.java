package app.planner.endpoint.service.type;

import app.planner.endpoint.country.type.CountryResponse;
import app.planner.endpoint.servicetype.type.ServiceTypeResponse;

import java.util.List;

public record CountryAndServiceTypeResponse(
        List<ServiceTypeResponse> serviceTypes,
        List<CountryResponse> countries) { }
