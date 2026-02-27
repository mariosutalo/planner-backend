package app.planner.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PGobject;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class PgJsonUtil {


    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode toJsonNode(ResultSet rs, String columnName) throws SQLException {
        Object obj = rs.getObject(columnName);
        if (obj == null) return null;
        try {
            if (obj instanceof PGobject pgObject) {
                return mapper.readTree(pgObject.getValue());
            }
            return mapper.readTree(obj.toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert column '" + columnName + "' to JsonNode", e);
        }
    }

}
