-- liquibase formatted sql

-- changeset Vasiliy:insert-data-car-showrooms
INSERT INTO car_showrooms (id, name, address) VALUES
('0b20ec55-571f-4b2a-a9aa-45902e944012', 'Car dealership Central', '3207 Sycamore Street, San Jose, California'),
('fc607028-2598-499f-ab12-2a3951c79cd5', 'Prestige car dealership', '1561 Dovetail Drive, IL, Illinois');

-- changeset Vasiliy:insert-data-categories
INSERT INTO categories (id, name) VALUES
('8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', 'SUV'),
('0f260839-23c2-4a9d-b68a-23f3ff3df4dd', 'Sedan'),
('5c8a82ba-a08e-4102-95e2-a5590853fbec', 'Hatchback');

-- changeset Vasiliy:insert-data-cars
INSERT INTO cars (id, release_year, model, make, price, category_id, car_showroom_id) VALUES
('48d42fe5-4ce2-4999-8cbb-05425fe4be41', 2009,'Mustang','Ford', 43736, '8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', '0b20ec55-571f-4b2a-a9aa-45902e944012'),
('bc2ef649-121f-4e57-8cac-c746f1bb15ca', 1995,'626','Mazda', 13592, '0f260839-23c2-4a9d-b68a-23f3ff3df4dd', '0b20ec55-571f-4b2a-a9aa-45902e944012'),
('c6c9edee-0d67-45da-b5b4-52c156d16f6a', 1979,'Grand Prix','Pontiac', 51178, '5c8a82ba-a08e-4102-95e2-a5590853fbec', 'fc607028-2598-499f-ab12-2a3951c79cd5'),
('0da90219-c8c1-4936-89e2-16dc01396b81', 2004,'Safari','GMC', 4714, '5c8a82ba-a08e-4102-95e2-a5590853fbec', '0b20ec55-571f-4b2a-a9aa-45902e944012'),
('3bebb449-c291-49ab-bc26-b9214343fd6b', 2011,'Cooper Countryman','MINI', 51323, '8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', '0b20ec55-571f-4b2a-a9aa-45902e944012'),
('67165647-ab83-453c-980e-d75fee53b55a', 2009,'STS','Cadillac', 6042, '0f260839-23c2-4a9d-b68a-23f3ff3df4dd', 'fc607028-2598-499f-ab12-2a3951c79cd5'),
('c7c4d952-a171-4bca-866a-ade786d44e97', 2010,'Crown Victoria','Ford', 33762, '8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', null),
('87ee6364-0680-48d8-b0c3-3db8e63e03ad', 2012,'JUKE','Nissan', 12430, '5c8a82ba-a08e-4102-95e2-a5590853fbec', 'fc607028-2598-499f-ab12-2a3951c79cd5'),
('a6e222ae-a260-4411-89a5-d9ae8c749164', 2001,'Tacoma','Toyota', 59599, '5c8a82ba-a08e-4102-95e2-a5590853fbec', 'fc607028-2598-499f-ab12-2a3951c79cd5'),
('99f01aca-e8a0-401d-ac74-502e791616f5', 2012,'CC','Volkswagen', 7748, '8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', null),
('4c395a5a-a877-4de3-acb3-d10762efd852', 2004,'Charger','Dodge', 51958, '8b10ccfc-545c-4dba-bd0a-0c7954bc6e61', null);

-- changeset Vasiliy:insert-data-clients
INSERT INTO clients (id, name, registration_date) VALUES
('d1e9a99f-efdb-4f92-a7ad-937b6797e1cd', 'Ivan Ivanov', '2025-01-05'),
('e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e', 'Alex Sidorov', '2024-12-30'),
('fc9c6f58-c3cb-42b4-ade9-fdba78d7334d', 'John Doe', '2024-12-01'),
('b554057a-818d-4d39-bd13-6d9377e56045', 'Frank Foster', '2024-12-15');

-- changeset Vasiliy:insert-data-client_contacts
INSERT INTO client_contacts (client_id, contact_info, contact_type) VALUES
('d1e9a99f-efdb-4f92-a7ad-937b6797e1cd', 'ivan.ivanov@example.com', 'email'),
('d1e9a99f-efdb-4f92-a7ad-937b6797e1cd', '(206) 342-8631', 'phone'),
('e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e', 'alexey.sidorov@example.com', 'email'),
('e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e', '(979) 565-0217', 'phone'),
('fc9c6f58-c3cb-42b4-ade9-fdba78d7334d', 'john.doe@example.com', 'email'),
('fc9c6f58-c3cb-42b4-ade9-fdba78d7334d', '(913) 438-5307', 'phone'),
('b554057a-818d-4d39-bd13-6d9377e56045', 'frank.foster@example.com', 'email'),
('b554057a-818d-4d39-bd13-6d9377e56045', '(302) 290-1464', 'phone');

-- changeset Vasiliy:insert-data-clients_cars
INSERT INTO clients_cars (cars_id, client_id) VALUES
('48d42fe5-4ce2-4999-8cbb-05425fe4be41', 'd1e9a99f-efdb-4f92-a7ad-937b6797e1cd'),
('c6c9edee-0d67-45da-b5b4-52c156d16f6a', 'd1e9a99f-efdb-4f92-a7ad-937b6797e1cd'),
('0da90219-c8c1-4936-89e2-16dc01396b81', 'e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e'),
('3bebb449-c291-49ab-bc26-b9214343fd6b', 'e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e'),
('67165647-ab83-453c-980e-d75fee53b55a', 'fc9c6f58-c3cb-42b4-ade9-fdba78d7334d'),
('87ee6364-0680-48d8-b0c3-3db8e63e03ad', 'b554057a-818d-4d39-bd13-6d9377e56045'),
('a6e222ae-a260-4411-89a5-d9ae8c749164', 'b554057a-818d-4d39-bd13-6d9377e56045'),
('bc2ef649-121f-4e57-8cac-c746f1bb15ca', 'b554057a-818d-4d39-bd13-6d9377e56045');

-- changeset Vasiliy:insert-data-reviews
INSERT INTO reviews (id, content, rating, client_id, car_id) VALUES
('f88fe5fd-3a4a-4bcc-b801-66543990fe2d', 'Mediocre car', 7, 'd1e9a99f-efdb-4f92-a7ad-937b6797e1cd', 'c6c9edee-0d67-45da-b5b4-52c156d16f6a'),
('b4da370e-bccc-498d-aa0a-e6cc64decd3e', 'Normal car', 8, 'fc9c6f58-c3cb-42b4-ade9-fdba78d7334d', '67165647-ab83-453c-980e-d75fee53b55a'),
('c0ef5542-584d-444e-a015-f96ecb6f4265', 'Great', 9, 'b554057a-818d-4d39-bd13-6d9377e56045', '87ee6364-0680-48d8-b0c3-3db8e63e03ad'),
('5d2bd26b-d14e-46c3-b37a-0ef4ae4835a8', 'The best car', 10, 'e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e', '3bebb449-c291-49ab-bc26-b9214343fd6b'),
('955e3feb-5632-410d-86f1-3453b847e70d', 'Bad car', 1, 'e2f4b7ef-31a7-4d6f-8b83-1d3f8e2a1a7e', '0da90219-c8c1-4936-89e2-16dc01396b81');