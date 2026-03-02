package app.planner.endpoint.service;

import app.planner.endpoint.serviceproperty.ServicePropertyValidator;
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
import app.planner.endpoint.service.type.CreateServiceRequest;
import app.planner.endpoint.service.type.ServiceCreatedResponse;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServicePropertyValidator servicePropertyValidator;
    private final ServiceRepository serviceRepository;

    public @Nullable String testCall() {
        return null;
    }

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
        service.setTitle(request.title());

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

}
