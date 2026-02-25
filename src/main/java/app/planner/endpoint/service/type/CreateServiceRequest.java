package app.planner.endpoint.service.type;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.node.ObjectNode;

public record CreateServiceRequest(

        @NotNull
        UUID ownerId,

        @NotNull
        Long serviceTypeId,

        @NotBlank
        @Size(max = 255)
        String title,

        @DecimalMin(value = "0.01", message = "Start price must be greater than 0")
        @Digits(integer = 7, fraction = 2)
        BigDecimal startPrice,

        @DecimalMin(value = "0.01", message = "End price must be greater than 0")
        @Digits(integer = 7, fraction = 2)
        BigDecimal endPrice,

        @NotNull
        JsonNode properties, // or Map<String, Object> if deserializing JSONB

        @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
        Double latitude,

        @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
        Double longitude,

        @Email
        String email,

        @Pattern(regexp = "^\\+?[0-9\\s\\-().]{7,20}$")
        String phoneNumber,

        @Size(max = 255)
        String streetAddress

) {
    public CreateServiceRequest {
        if (startPrice != null && endPrice != null && endPrice.compareTo(startPrice) < 0) {
            throw new IllegalArgumentException("End price must be greater than or equal to start price");
        }
    }
}

