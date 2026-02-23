CREATE TYPE data_type AS ENUM ('int', 'text', 'boolean');

CREATE TABLE service_type (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE service_property_definition (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    service_type_id BIGINT NOT NULL REFERENCES service_type(id),
    name TEXT NOT NULL,
    data_type data_type NOT NULL,
    required BOOLEAN NOT NULL DEFAULT false,
    options JSONB
);
