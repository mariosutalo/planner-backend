package app.planner.endpoint.service.type;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceSearchForTableRequest {
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String term = "";

    @Min(value = 1, message = "Current page must be 1 or greater")
    private int page = 1;

    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 100, message = "Page size must not exceed 100")
    private int pageSize = 5;

}
