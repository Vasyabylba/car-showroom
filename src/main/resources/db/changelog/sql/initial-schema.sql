-- liquibase formatted sql

-- changeset Vasiliy:create-table-car_showrooms
CREATE TABLE car_showrooms (id UUID NOT NULL, name VARCHAR(255) NOT NULL, address VARCHAR(255) NOT NULL, CONSTRAINT pk_car_showrooms PRIMARY KEY (id));

-- changeset Vasiliy:create-table-cars
CREATE TABLE cars (id UUID NOT NULL, model VARCHAR(255), make VARCHAR(255), release_year INTEGER NOT NULL, price DECIMAL(19, 2), category_id UUID, car_showroom_id UUID, CONSTRAINT pk_cars PRIMARY KEY (id));

-- changeset Vasiliy:create-table-categories
CREATE TABLE categories (id UUID NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT pk_categories PRIMARY KEY (id));

-- changeset Vasiliy:create-table-client_contacts
CREATE TABLE client_contacts (client_id UUID NOT NULL, contact_info VARCHAR(255), contact_type VARCHAR(255) NOT NULL, CONSTRAINT pk_client_contacts PRIMARY KEY (client_id, contact_type));

-- changeset Vasiliy:create-table-clients
CREATE TABLE clients (id UUID NOT NULL, name VARCHAR(255) NOT NULL, registration_date date NOT NULL, CONSTRAINT pk_clients PRIMARY KEY (id));

-- changeset Vasiliy:create-table-clients_cars
CREATE TABLE clients_cars (cars_id UUID NOT NULL, client_id UUID NOT NULL, CONSTRAINT pk_clients_cars PRIMARY KEY (cars_id, client_id));

-- changeset Vasiliy:create-table-reviews
CREATE TABLE reviews (id UUID NOT NULL, content TEXT NOT NULL, rating INTEGER NOT NULL, client_id UUID NOT NULL, car_id UUID NOT NULL, CONSTRAINT pk_reviews PRIMARY KEY (id));

-- changeset Vasiliy:alter-table-categories
ALTER TABLE categories ADD CONSTRAINT uc_categories_name UNIQUE (name);

-- changeset Vasiliy:alter-table-cars-1
ALTER TABLE cars ADD CONSTRAINT FK_CARS_ON_CAR_SHOWROOM FOREIGN KEY (car_showroom_id) REFERENCES car_showrooms (id);

-- changeset Vasiliy:alter-table-cars-2
ALTER TABLE cars ADD CONSTRAINT FK_CARS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

-- changeset Vasiliy:alter-table-reviews-1
ALTER TABLE reviews ADD CONSTRAINT FK_REVIEWS_ON_CAR FOREIGN KEY (car_id) REFERENCES cars (id);

-- changeset Vasiliy:alter-table-reviews-2
ALTER TABLE reviews ADD CONSTRAINT FK_REVIEWS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

-- changeset Vasiliy:alter-table-clients_cars-1
ALTER TABLE clients_cars ADD CONSTRAINT fk_clicar_on_car FOREIGN KEY (cars_id) REFERENCES cars (id);

-- changeset Vasiliy:alter-table-clients_cars-2
ALTER TABLE clients_cars ADD CONSTRAINT fk_clicar_on_client FOREIGN KEY (client_id) REFERENCES clients (id);

-- changeset Vasiliy:alter-table-clients_cars-3
ALTER TABLE client_contacts ADD CONSTRAINT fk_client_contacts_on_client FOREIGN KEY (client_id) REFERENCES clients (id);

