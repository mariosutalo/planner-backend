package app.planner.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Converter(autoApply = true)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        if (attribute == null) return null;
        return attribute.toString();
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return mapper.readTree(dbData);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to convert JSON string to JsonNode", e);
        }
    }
}