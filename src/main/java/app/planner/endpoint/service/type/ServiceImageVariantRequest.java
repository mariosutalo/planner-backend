package app.planner.endpoint.service.type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServiceImageVariantRequest(

        @NotBlank
        String url,

        @NotNull
        @Positive
        Integer width,

        @NotNull
        @Positive
        Integer height

) {
}
