package app.planner.endpoint.service.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageType {
    COVER("cover"), GALLERY("gallery");

    private final String code;
    ImageType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }
}
