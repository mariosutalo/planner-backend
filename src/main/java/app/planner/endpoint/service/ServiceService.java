package app.planner.endpoint.service;

import app.planner.endpoint.country.CountryJdbcRepository;
import app.planner.endpoint.service.type.*;
import app.planner.endpoint.serviceproperty.ServicePropertyValidator;
import app.planner.endpoint.servicetype.ServiceTypeJdbcRepository;
import app.planner.sharedtypes.PaginatedResponse;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import app.planner.domain.ServiceImage;
import app.planner.domain.ServiceImageVariant;
import app.planner.domain.ServiceS;
import app.planner.domain.ServiceType;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServicePropertyValidator servicePropertyValidator;
    private final ServiceRepository serviceRepository;
    private final ServiceJdbcRepository serviceJdbcRepository;
    private final CountryJdbcRepository countryJdbcRepository;
    private final ServiceTypeJdbcRepository serviceTypeJdbcRepository;

    public ServiceCreatedResponse addNewService(CreateServiceRequest request, @Nullable String userUUID) {
        if (userUUID == null) {

            /*
             * throw new ResponseStatusException(
             * HttpStatus.INTERNAL_SERVER_ERROR, "User UUID is null");
             */
            // to do - fake uuid
            userUUID = UUID.randomUUID().toString();
        }

        servicePropertyValidator.validate(
                request.serviceTypeId(),
                request.properties());

        var service = new ServiceS();
        service.setEmail(request.email());
        service.setEndPrice(request.endPrice());
        service.setOwnerId(UUID.fromString(userUUID));
        service.setPhoneNumber(request.phoneNumber());

        var serviceType = new ServiceType();
        serviceType.setId(request.serviceTypeId());

        service.setServiceType(serviceType);

        var geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        var spotPosition = geometryFactory
                .createPoint(new Coordinate(request.longitude(), request.latitude()));
        service.setPosition(spotPosition);

        service.setProperties(request.properties().toString());
        service.setStartPrice(request.startPrice());
        service.setEndPrice(request.endPrice());
        service.setStreetAddress(request.streetAddress());
        service.setName(request.name());

        List<ServiceImage> serviceImages = request.serviceImagesApiData().stream().map(img -> {
            var serviceImage = new ServiceImage();
            serviceImage.setService(service);
            serviceImage.setImageType(img.imageType().getCode());
            var serviceImageVariants = img.variants().stream().map(variant -> {
                var imageVariant = new ServiceImageVariant();
                imageVariant.setServiceImage(serviceImage);
                imageVariant.setUrl(variant.url());
                imageVariant.setWidth(variant.width());
                imageVariant.setHeight(variant.height());
                return imageVariant;
            }).toList();
            serviceImage.setServiceImageVariants(new HashSet<>(serviceImageVariants));
            return serviceImage;
        }).toList();

        service.setServiceImages(new HashSet<>(serviceImages));

        var savedService = serviceRepository.save(service);
        return new ServiceCreatedResponse(savedService.getId());
    }

    public PaginatedResponse<List<ServiceTableResponse>> findServiceByOwner(ServiceSearchForTableRequest request,
                                                                            @Nullable String ownerUuid) {
        // to do
        /* if (ownerUuid == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid request");
        } */
        /*         var uuid = UUID.fromString(ownerUuid);
         */        // var spotNameWithWildcards =
        // SearchUtils.addWildCardsToString(spotParams.getTerm());
        var uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
        var offset = request.getPageSize() * (request.getPage() - 1);
        var spots = serviceJdbcRepository.getServicesForOwner(uuid, request.getPageSize(), offset);
        var count = serviceJdbcRepository.countServiceForOwner(uuid);
        return new PaginatedResponse<>(spots, request.getPage(), request.getPageSize(), count);
    }

    public CountryAndServiceTypeResponse getCountriesAndServiceTypes(String langCode) {
        var countries = countryJdbcRepository.getAllCountries();
        var serviceTypes = serviceTypeJdbcRepository.getServiceTypesLocalized(langCode);
        return new CountryAndServiceTypeResponse(serviceTypes, countries);
    }

}
