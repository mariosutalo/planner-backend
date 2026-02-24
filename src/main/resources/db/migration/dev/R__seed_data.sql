INSERT INTO service_type (name) VALUES
('Photo Booth'),
('Photographer'),
('Car Rental');

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'duration_hours', 'int', true, NULL
FROM service_type WHERE name = 'Photo Booth';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'instant_print', 'boolean', true, NULL
FROM service_type WHERE name = 'Photo Booth';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'backdrop_theme', 'text', false,
       '["Classic","Wedding","Birthday","Corporate"]'::jsonb
FROM service_type WHERE name = 'Photo Booth';




INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'event_type', 'text', true,
       '["Wedding","Birthday","Corporate","Portrait"]'::jsonb
FROM service_type WHERE name = 'Photographer';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'edited_photos', 'int', true, NULL
FROM service_type WHERE name = 'Photographer';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'video_included', 'boolean', false, NULL
FROM service_type WHERE name = 'Photographer';



INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'car_type', 'text', true,
       '["Sedan","SUV","Convertible","Van"]'::jsonb
FROM service_type WHERE name = 'Car Rental';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'transmission', 'text', true,
       '["Manual","Automatic"]'::jsonb
FROM service_type WHERE name = 'Car Rental';

INSERT INTO service_property_definition
(service_type_id, name, data_type, required, options)
SELECT id, 'air_conditioning', 'boolean', true, NULL
FROM service_type WHERE name = 'Car Rental';



INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Wedding Magic Booth', 300, 500,
'{
  "duration_hours": 5,
  "instant_print": true,
  "backdrop_theme": "Wedding"
}'::jsonb
FROM service_type WHERE name = 'Photo Booth';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Corporate Fun Booth', 250, 400,
'{
  "duration_hours": 4,
  "instant_print": true,
  "backdrop_theme": "Corporate"
}'::jsonb
FROM service_type WHERE name = 'Photo Booth';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Birthday Party Booth', 200, 350,
'{
  "duration_hours": 3,
  "instant_print": false,
  "backdrop_theme": "Birthday"
}'::jsonb
FROM service_type WHERE name = 'Photo Booth';


INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Premium Wedding Photography', 1200, 2500,
'{
  "event_type": "Wedding",
  "edited_photos": 300,
  "video_included": true
}'::jsonb
FROM service_type WHERE name = 'Photographer';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Corporate Event Photographer', 800, 1500,
'{
  "event_type": "Corporate",
  "edited_photos": 150,
  "video_included": false
}'::jsonb
FROM service_type WHERE name = 'Photographer';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Birthday Party Photographer', 500, 900,
'{
  "event_type": "Birthday",
  "edited_photos": 120,
  "video_included": false
}'::jsonb
FROM service_type WHERE name = 'Photographer';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Portrait Session Pro', 300, 600,
'{
  "event_type": "Portrait",
  "edited_photos": 50,
  "video_included": false
}'::jsonb
FROM service_type WHERE name = 'Photographer';


INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Luxury SUV Rental', 150, 250,
'{
  "car_type": "SUV",
  "transmission": "Automatic",
  "air_conditioning": true
}'::jsonb
FROM service_type WHERE name = 'Car Rental';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Convertible Weekend Special', 200, 350,
'{
  "car_type": "Convertible",
  "transmission": "Automatic",
  "air_conditioning": true
}'::jsonb
FROM service_type WHERE name = 'Car Rental';

INSERT INTO service (service_type_id, owner_id, title, start_price, end_price, properties)
SELECT id,
       '11111111-1111-1111-1111-111111111111'::uuid,
       'Budget Sedan Rental', 70, 120,
'{
  "car_type": "Sedan",
  "transmission": "Manual",
  "air_conditioning": true
}'::jsonb
FROM service_type WHERE name = 'Car Rental';


