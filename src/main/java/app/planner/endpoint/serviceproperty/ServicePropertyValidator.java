package app.planner.endpoint.serviceproperty;

import app.planner.domain.DataType;
import app.planner.domain.ServicePropertyDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicePropertyValidator {

    private final ServicePropertyDefinitionRepository definitionRepository;

    public void validate(Long serviceTypeId, Map<String, Object> properties) {
        List<ServicePropertyDefinition> definitions = definitionRepository.findByServiceTypeId(serviceTypeId);

        List<String> errors = new ArrayList<>();

        for (ServicePropertyDefinition def : definitions) {
            Object value = properties.get(def.getName());

            // Check required
            if (value == null) {
                if (def.isRequired()) {
                    errors.add("Missing required property: " + def.getName());
                }
                continue;
            }

            // Check type
            if (!isValidType(value, def.getDataType())) {
                errors.add("Property '" + def.getName() + "' must be of type " + def.getDataType());
                continue;
            }

            // Check options (allowed values) if present
            if (def.getOptions() != null) {
                validateOptions(def, value, errors);
            }
        }

        // Check for unknown properties (optional — remove if you want to allow extra fields)
        Set<String> definedNames = definitions.stream()
                .map(ServicePropertyDefinition::getName)
                .collect(Collectors.toSet());
        for (String key : properties.keySet()) {
            if (!definedNames.contains(key)) {
                errors.add("Unknown property: " + key);
            }
        }

        if (!errors.isEmpty()) {
            throw new ServicePropertyValidationException(errors);
        }
    }

    private boolean isValidType(Object value, DataType dataType) {
        return switch (dataType) {
            case TEXT -> value instanceof String;
            case INT -> value instanceof Integer;
            case DECIMAL -> value instanceof Number;
            case BOOLEAN -> value instanceof Boolean;
            case MULTIPLE -> value instanceof List;
            case SINGLE -> value instanceof String;
        };
    }

    private void validateOptions(ServicePropertyDefinition def, Object value, List<String> errors) {
        // Example options: {"allowed": ["industrial", "residential", "commercial"]}
        Object allowed = def.getOptions().get("allowed");
        if (allowed instanceof List<?> allowedList) {
            if (!allowedList.contains(value)) {
                errors.add("Property '" + def.getName() + "' must be one of: " + allowedList);
            }
        }

        // Example options: {"min": 0, "max": 100}
        if (value instanceof Number num) {
            Object min = def.getOptions().get("min");
            Object max = def.getOptions().get("max");
            if (min instanceof Number && num.doubleValue() < ((Number) min).doubleValue()) {
                errors.add("Property '" + def.getName() + "' must be >= " + min);
            }
            if (max instanceof Number && num.doubleValue() > ((Number) max).doubleValue()) {
                errors.add("Property '" + def.getName() + "' must be <= " + max);
            }
        }

        if (value instanceof String str) {
            Object minLength = def.getOptions().get("minLength");
            Object maxLength = def.getOptions().get("maxLength");
            if (minLength instanceof Number && str.length() < ((Number) minLength).intValue()) {
                errors.add("Property '" + def.getName() + "' must be at least " + minLength + " characters");
            }
            if (maxLength instanceof Number && str.length() > ((Number) maxLength).intValue()) {
                errors.add("Property '" + def.getName() + "' must be at most " + maxLength + " characters");
            }
        }
    }
}
