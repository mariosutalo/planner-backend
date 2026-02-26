CREATE TYPE data_type AS ENUM ('int', 'text', 'boolean', 'decimal', 'single', 'multiple');
CREATE TYPE image_type_enum AS ENUM ('cover', 'gallery');

CREATE EXTENSION IF NOT EXISTS postgis;

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

CREATE TABLE service (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner_id uuid NOT NULL,
    service_type_id BIGINT NOT NULL REFERENCES service_type(id),
    title TEXT NOT NULL,
    start_price decimal(9,2),
    end_price decimal (9,2),
    properties JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    position geography(point, 4326),
    email TEXT,
    phone_number TEXT,
    street_address TEXT,

    CONSTRAINT check_price_logic CHECK (
    (start_price IS NULL OR start_price > 0) AND
    (end_price IS NULL OR end_price > 0) AND
    (start_price IS NULL OR end_price IS NULL OR end_price >= start_price))
);


CREATE TABLE service_image (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    service_id BIGINT NOT NULL,
    image_type image_type_enum NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    FOREIGN KEY (service_id) REFERENCES service (id) ON DELETE CASCADE
);


CREATE TABLE service_image_variant (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    service_image_id BIGINT NOT NULL,
    url VARCHAR(2083) NOT NULL UNIQUE,
    width INT,
    height INT,
    FOREIGN KEY (service_image_id) REFERENCES service_image (id) ON DELETE CASCADE
);

CREATE TABLE favorite_service (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    service_id BIGINT NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    FOREIGN KEY (service_id) REFERENCES service (id) ON DELETE CASCADE,
    CONSTRAINT unique_user_service UNIQUE (service_id, user_id)
);