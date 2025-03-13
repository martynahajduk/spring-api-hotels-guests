CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO hotels (hotel_id, name, location) VALUES
                                                  (gen_random_uuid(), 'Steigenberger Wiltcher''s', 'Brussels'),
                                                  (gen_random_uuid(), 'Hotel De Witte Lelie', 'Brussels'),
                                                  (gen_random_uuid(), 'Hotel ''t Sandt Antwerpen', 'Antwerp'),
                                                  (gen_random_uuid(), 'Hotel Julien Antwerp', 'Antwerp'),
                                                  (gen_random_uuid(), 'Pillows Grand Hotel Reylof', 'Ghent');

INSERT INTO rooms (room_number, price, floor, room_type, hotel_id) VALUES
                                                                       (101, 220.0, 1, 'SINGLE', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                       (201, 300.0, 2, 'DOUBLE', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                       (301, 450.10, 3, 'TWIN', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                       (401, 500.0, 4, 'SUITE', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                       (501, 600.0, 5, 'DELUXE', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),

                                                                       (101, 220.0, 1, 'SINGLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                       (201, 300.0, 2, 'DOUBLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                       (301, 450.10, 3, 'TWIN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                       (401, 500.0, 4, 'SUITE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                       (501, 600.0, 5, 'DELUXE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),

                                                                       (101, 220.0, 1, 'SINGLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                       (201, 300.0, 2, 'DOUBLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                       (301, 450.10, 3, 'TWIN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                       (401, 500.0, 4, 'SUITE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                       (501, 600.0, 5, 'DELUXE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),

                                                                       (101, 220.0, 1, 'SINGLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                       (201, 300.0, 2, 'DOUBLE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                       (301, 450.10, 3, 'TWIN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                       (401, 500.0, 4, 'SUITE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                       (501, 600.0, 5, 'DELUXE', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),

                                                                       (101, 220.0, 1, 'SINGLE', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                       (201, 300.0, 2, 'DOUBLE', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                       (301, 450.10, 3, 'TWIN', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                       (401, 500.0, 4, 'SUITE', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                       (501, 600.0, 5, 'DELUXE', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'));

INSERT INTO guests (guest_id, name, date_of_birth, nationality, hotel_id) VALUES
                                                                              (gen_random_uuid(), 'Aria Voss', '1993-04-11', 'AUSTRALIAN', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                              (gen_random_uuid(), 'Lysander Quinn', '1988-08-24', 'BELGIAN', (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s')),
                                                                              (gen_random_uuid(), 'Dorian Thorne', '1985-02-03', 'BRAZILIAN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                              (gen_random_uuid(), 'Felix Hawke', '1991-11-10', 'CROATIAN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie')),
                                                                              (gen_random_uuid(), 'Niamh Callahan', '1987-07-19', 'SPANISH', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                              (gen_random_uuid(), 'Isolde Ravenswood', '1994-12-25', 'AMERICAN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen')),
                                                                              (gen_random_uuid(), 'Orion Ashford', '1990-03-30', 'HUNGARIAN', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                              (gen_random_uuid(), 'Selene Hawthorne', '1989-06-14', 'GREEK', (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp')),
                                                                              (gen_random_uuid(), 'Calista Evernight', '1995-01-18', 'ROMANIAN', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                              (gen_random_uuid(), 'Soren Albrecht', '1983-09-05', 'ROMANIAN', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                              (gen_random_uuid(), 'Alina Dimova', '2004-11-05', 'MOLDOVAN', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof')),
                                                                              (gen_random_uuid(), 'Carys Coetzee', '2004-06-08', 'SOUTH_AFRICAN', (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'));

INSERT INTO guest_rooms (guest_id, hotel_id, room_number) VALUES
                                                              ((SELECT guest_id FROM guests WHERE name = 'Aria Voss'), (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s'), 101),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Lysander Quinn'), (SELECT hotel_id FROM hotels WHERE name = 'Steigenberger Wiltcher''s'), 201),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Dorian Thorne'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie'), 401),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Dorian Thorne'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie'), 501),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Felix Hawke'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel De Witte Lelie'), 501),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Niamh Callahan'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen'), 101),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Isolde Ravenswood'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel ''t Sandt Antwerpen'), 201),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Orion Ashford'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp'), 301),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Orion Ashford'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp'), 401),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Selene Hawthorne'), (SELECT hotel_id FROM hotels WHERE name = 'Hotel Julien Antwerp'), 401),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Calista Evernight'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 301),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Soren Albrecht'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 401),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Alina Dimova'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 301),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Alina Dimova'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 401),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Alina Dimova'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 501),
                                                              ((SELECT guest_id FROM guests WHERE name = 'Carys Coetzee'), (SELECT hotel_id FROM hotels WHERE name = 'Pillows Grand Hotel Reylof'), 501);

INSERT INTO app_users (username, password, role) VALUES
                                                     ('admin_test', '$2b$12$M6UjizyvBZ96VvCy9uT7BuOan5JJSUfhRKqMLDZ/OL7KHCO0cGlvK', 'ADMIN'),
                                                     ('user_test', '$2b$12$mGhp1sgSp8TDKMr0BzzOFeT/AMPcjnuoMmWKx/wOBfr.HMaQNAbei', 'USER');

UPDATE guest_rooms
SET owner_id = (SELECT id FROM app_users WHERE username = 'user_test')
WHERE guest_id IN (
    SELECT guest_id FROM guests WHERE name IN ('Alina Dimova', 'Carys Coetzee')
);

