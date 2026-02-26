package app.planner.endpoint.serviceproperty;

import app.planner.domain.DataType;
import app.planner.domain.ServicePropertyDefinition;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ServicePropertyValidator {

    private final ServicePropertyDefinitionRepository definitionRepository;

    public void validate(Long serviceTypeId, JsonNode properties) {
        List<ServicePropertyDefinition> definitions = definitionRepository.findByServiceTypeId(serviceTypeId);

        List<String> errors = new ArrayList<>();

        for (ServicePropertyDefinition def : definitions) {
            JsonNode value = properties.get(def.getName());

            // Check required
            if (value == null || value.isNull()) {
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

            // Check options constraints if present
            if (def.getOptions() != null && !def.getOptions().isNull()) {
                validateOptions(def, value, errors);
            }
        }

        // Check for unknown properties
        Set<String> definedNames = definitions.stream()
                .map(ServicePropertyDefinition::getName)
                .collect(Collectors.toSet());
        properties.properties().forEach(entry -> {
            if (!definedNames.contains(entry.getKey())) {
                errors.add("Unknown property: " + entry.getKey());
            }
        });

        if (!errors.isEmpty()) {
            throw new ServicePropertyValidationException(errors);
        }
    }

    private boolean isValidType(JsonNode value, DataType dataType) {
        return switch (dataType) {
            case TEXT -> value.isString();
            case INT -> value.isIntegralNumber();
            case DECIMAL -> value.isNumber();
            case BOOLEAN -> value.isBoolean();
            case MULTIPLE -> value.isArray();
            case SINGLE -> value.isString();
        };
    }

    private void validateOptions(ServicePropertyDefinition def, JsonNode value, List<String> errors) {
        JsonNode options = def.getOptions();

        // JSON array → list of allowed values (used for SINGLE and MULTIPLE)
        if (options.isArray()) {
            List<String> allowedList = StreamSupport.stream(options.spliterator(), false)
                    .map(JsonNode::asString)
                    .toList();

            if (def.getDataType() == DataType.SINGLE) {
                if (!allowedList.contains(value.asString())) {
                    errors.add("Property '" + def.getName() + "' must be one of: " + allowedList);
                }
            } else if (def.getDataType() == DataType.MULTIPLE && value.isArray()) {
                StreamSupport.stream(value.spliterator(), false).forEach(selected -> {
                    if (!allowedList.contains(selected.asString())) {
                        errors.add("Property '" + def.getName() + "': invalid value '" + selected.asString() + "', must be one of: " + allowedList);
                    }
                });
            }
        }

        // JSON object → range/length constraints (used for INT, DECIMAL, TEXT)
        if (options.isObject()) {
            if (value.isNumber()) {
                JsonNode min = options.get("min");
                JsonNode max = options.get("max");
                if (min != null && !min.isNull() && value.doubleValue() < min.asDouble()) {
                    errors.add("Property '" + def.getName() + "' must be >= " + min.asDouble());
                }
                if (max != null && !max.isNull() && value.doubleValue() > max.asDouble()) {
                    errors.add("Property '" + def.getName() + "' must be <= " + max.asDouble());
                }
            }

            if (value.isString()) {
                String str = value.asString();
                JsonNode minLength = options.get("minLength");
                JsonNode maxLength = options.get("maxLength");
                if (minLength != null && !minLength.isNull() && str.length() < minLength.asInt()) {
                    errors.add("Property '" + def.getName() + "' must be at least " + minLength.asInt() + " characters");
                }
                if (maxLength != null && !maxLength.isNull() && str.length() > maxLength.asInt()) {
                    errors.add("Property '" + def.getName() + "' must be at most " + maxLength.asInt() + " characters");
                }
            }
        }
    }
}