package app.planner.endpoint.service.type;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ServiceImageRequest(

        @NotBlank
        String name,

        @NotNull
        ImageType imageType,

        @NotEmpty
        @Valid
        List<ServiceImageVariantRequest> variants

) {
}
