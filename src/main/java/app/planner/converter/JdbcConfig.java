package app.planner.converter;

import java.util.List;

import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return List.of(new PGobjectToJsonNodeConverter());
    }

    @ReadingConverter
    public static class PGobjectToJsonNodeConverter implements Converter<PGobject, JsonNode> {
        private static final ObjectMapper mapper = new ObjectMapper();

        @Override
        public JsonNode convert(PGobject source) {
            try {
                return mapper.readTree(source.getValue());
            } catch (JacksonException e) {
                throw new RuntimeException(e);
            }
        }
    }
}