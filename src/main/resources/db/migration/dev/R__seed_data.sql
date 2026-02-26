DELETE FROM service;
DELETE FROM service_property_definition;
DELETE FROM service_type;

ALTER SEQUENCE service_id_seq RESTART WITH 1;
ALTER SEQUENCE service_property_definition_id_seq RESTART WITH 1;
ALTER SEQUENCE service_type_id_seq RESTART WITH 1;


INSERT INTO service_type (name) VALUES
('photo_booth_s'),
('photographer_s'),
('car_rental_s');

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'duration_hours_p', 'INT', true, '{"min": 4, "max": 10}'
FROM service_type WHERE name = 'photo_booth_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'instant_print_p', 'BOOLEAN', true, NULL
FROM service_type WHERE name = 'photo_booth_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'backdrop_theme_p', 'SINGLE', false,
       '["Light","Dark"]'::jsonb
FROM service_type WHERE name = 'photo_booth_s';


INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'event_type_p', 'MULTIPLE', true,
       '["Wedding","Birthday","Corporate","Portrait"]'::jsonb
FROM service_type WHERE name = 'photographer_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'edited_photos_p', 'INT', true, NULL
FROM service_type WHERE name = 'photographer_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'air_conditioning_p', 'BOOLEAN', true, NULL
FROM service_type WHERE name = 'car_rental_s';

INSERT INTO service_property_definition (service_type_id, name, data_type, required, options)
SELECT id, 'car_type_p', 'SINGLE', true,
       '["Sedan","SUV","Convertible","Van"]'::jsonb
FROM service_type WHERE name = 'car_rental_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'transmission_p', 'SINGLE', true,
       '["Manual","Automatic"]'::jsonb
FROM service_type WHERE name = 'car_rental_s';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'air_conditioning_p', 'BOOLEAN', true, NULL
FROM service_type WHERE name = 'car_rental_s';

-- ─── photo_booth_s services ───────────────────────────────────────────────────

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440001'::uuid, st.id,
       'Glamour Shot Photo Booth',
       150.00, 400.00,
       '{"duration_hours_p": 4, "instant_print_p": true, "backdrop_theme_p": "Light"}'::jsonb,
    ST_SetSRID(ST_MakePoint(16.4401, 43.5081), 4326),
       'glamourshot@photobooth.hr', '+385 21 123 4567', 'Marmontova 5, 21000 Split'
FROM service_type st WHERE st.name = 'photo_booth_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440002'::uuid, st.id,
       'Snap & Print Photo Booth',
       200.00, 500.00,
       '{"duration_hours_p": 6, "instant_print_p": false, "backdrop_theme_p": "Dark"}'::jsonb,
    ST_SetSRID(ST_MakePoint(15.9819, 45.8150), 4326),
       'snapprint@photobooth.hr', '+385 1 234 5678', 'Ilica 10, 10000 Zagreb'
FROM service_type st WHERE st.name = 'photo_booth_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440003'::uuid, st.id,
       'Memories Photo Booth',
       100.00, 300.00,
       '{"duration_hours_p": 5, "instant_print_p": true}'::jsonb,
    ST_SetSRID(ST_MakePoint(13.8497, 45.3271), 4326),
       'memories@photobooth.hr', '+385 52 123 4567', 'Sergeja Jesenjina 2, 52100 Pula'
FROM service_type st WHERE st.name = 'photo_booth_s';


-- ─── photographer_s services ──────────────────────────────────────────────────

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440004'::uuid, st.id,
       'Ivan Photography',
       300.00, 800.00,
       '{"event_type_p": ["Wedding", "Portrait"], "edited_photos_p": 200, "video_included_p": true}'::jsonb,
    ST_SetSRID(ST_MakePoint(18.0944, 42.6507), 4326),
       'ivan@photography.hr', '+385 20 321 9876', 'Stradun 3, 20000 Dubrovnik'
FROM service_type st WHERE st.name = 'photographer_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440005'::uuid, st.id,
       'Lens & Moment Studio',
       250.00, 600.00,
       '{"event_type_p": ["Birthday", "Corporate"], "edited_photos_p": 150}'::jsonb,
    ST_SetSRID(ST_MakePoint(15.9819, 45.8150), 4326),
       'lensmoment@studio.hr', '+385 1 987 6543', 'Varsavska 12, 10000 Zagreb'
FROM service_type st WHERE st.name = 'photographer_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440006'::uuid, st.id,
       'Coastal Clicks Photography',
       400.00, 1000.00,
       '{"event_type_p": ["Wedding", "Birthday", "Corporate"], "edited_photos_p": 300, "video_included_p": true}'::jsonb,
    ST_SetSRID(ST_MakePoint(16.4401, 43.5081), 4326),
       'coastal@clicks.hr', '+385 21 555 7890', 'Riva 8, 21000 Split'
FROM service_type st WHERE st.name = 'photographer_s';


-- ─── car_rental_s services ────────────────────────────────────────────────────

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440007'::uuid, st.id,
       'Adriatic Car Rentals',
       50.00, 120.00,
       '{"car_type_p": "SUV", "transmission_p": "Automatic", "air_conditioning_p": true}'::jsonb,
    ST_SetSRID(ST_MakePoint(16.4401, 43.5081), 4326),
       'rent@adriatic.hr', '+385 21 444 1234', 'Domovinskog rata 15, 21000 Split'
FROM service_type st WHERE st.name = 'car_rental_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440008'::uuid, st.id,
       'Zagreb Drive',
       40.00, 90.00,
       '{"car_type_p": "Sedan", "transmission_p": "Manual", "air_conditioning_p": true}'::jsonb,
    ST_SetSRID(ST_MakePoint(15.9819, 45.8150), 4326),
       'info@zagrebdrive.hr', '+385 1 111 2233', 'Branimirova 4, 10000 Zagreb'
FROM service_type st WHERE st.name = 'car_rental_s';

INSERT INTO service (owner_id, service_type_id, title, start_price, end_price, properties, position, email, phone_number, street_address)
SELECT '550e8400-e29b-41d4-a716-446655440009'::uuid, st.id,
       'Island Wheels Rental',
       60.00, 150.00,
       '{"car_type_p": "Convertible", "transmission_p": "Automatic", "air_conditioning_p": false}'::jsonb,
    ST_SetSRID(ST_MakePoint(15.2314, 44.1194), 4326),
       'wheels@island.hr', '+385 23 777 8899', 'Široka ulica 6, 23000 Zadar'
FROM service_type st WHERE st.name = 'car_rental_s';


