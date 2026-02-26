package app.planner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

import java.util.Map;

@Setter
@Getter
@Entity
public class ServicePropertyDefinition {
    @Id
    @GeneratedValue
    private Long id;
    private Long serviceTypeId;
    private String name;

    @Enumerated(EnumType.STRING)
    private DataType dataType;

    private boolean required;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode options;

}