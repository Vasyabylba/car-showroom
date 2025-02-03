<h1 align="center">Car Showroom</h1>

## Description

Car Showroom is a service providing a RESTful API for managing car sales, showrooms, clients, and reviews The application allows users to:
- Search, add, update, and delete cars, showrooms, clients, and categories.
- Assign cars to showrooms and clients.
- Search for cars using various filters.
- Manage customer reviews for purchased cars.
- Handle errors efficiently with a global exception handler.

## Technologies Used

- **Java**
- **Spring Framework**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Liquibase**
- **MapStruct**
- **Lombok**
- **Gradle**

## Features

- **CRUD Operations** for all entities.
- **Pagination and Sorting** when listing cars.
- **Filtering Mechanism** for car searches.
- **Client-Showroom-Car Assignment**
- **Customer Reviews** for purchased cars.
- **Validation** for input parameters.
- **Transaction Management** configured within the application, enabling the use of `@Transactional` for ensuring data consistency.
- **Error Handling** with structured response messages.
- **Custom BeanPostProcessor** for logging of incoming and outgoing parameters of all or specific methods to the specified objects with one custom annotation `@Logging`
- **Deployable as a WAR** for Tomcat.

## API Endpoints

### Cars (`/api/v1/cars`)

| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/` | Get all cars with filtering and pagination |
| `GET` | `/{id}` | Get a single car by ID |
| `POST` | `/` | Add a new car |
| `PUT` | `/{id}` | Update an existing car |
| `DELETE` | `/{id}` | Remove a car |
| `POST` | `/{carId}/showroom` | Assign a car to a showroom |

### Showrooms (`/api/v1/carshowrooms`)

| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/` | Get all car showrooms |
| `GET` | `/{id}` | Get a showroom by ID |
| `POST` | `/` | Add a new showroom |
| `PUT` | `/{id}` | Update an existing showroom |
| `DELETE` | `/{id}` | Remove a showroom |
| `GET` | `/{showroomId}/cars` | List all cars in a showroom |

### Categories (`/api/v1/categories`)

| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/` | Get all categories |
| `GET` | `/{id}` | Get a category by ID |
| `POST` | `/` | Add a new category |
| `PUT` | `/{id}` | Update an existing category |
| `DELETE` | `/{id}` | Remove a category |

### Clients (`/api/v1/clients`)

| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/` | Get all clients |
| `GET` | `/{id}` | Get a client by ID |
| `POST` | `/` | Add a new client |
| `PUT` | `/{id}` | Update an existing client |
| `DELETE` | `/{id}` | Remove a client |
| `POST` | `/{clientId}/car` | Assign a car to a client |

### Reviews (`/api/v1/reviews`)

| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/` | Get all reviews with filtering |
| `GET` | `/{id}` | Get a review by ID |
| `POST` | `/` | Add a new review |
| `PUT` | `/{id}` | Update an existing review |
| `DELETE` | `/{id}` | Remove a review |

## Error Handling

The application handles errors using `RestControllerAdvice`. Below are some key exceptions:
- **`422 UNPROCESSABLE ENTITY`**: Entity not found (e.g., when a requested car, showroom, client, or review does not exist)
- **`409 CONFLICT`**: Business logic violations (e.g., when attempting to assign an already assigned car)
- **`400 BAD REQUEST`**: Validation errors (e.g., invalid parameters)

## How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/Vasyabylba/car-showroom.git
   ```
2. Configure PostgreSQL database and update `application.yml`.
3. Build and run the application:
   ```sh
   ./gradlew bootRun
   ```
4. The API will be available at `http://localhost:8080/api/v1`.

## Deployment

The project includes a Gradle `war` plugin to deploy on Tomcat. Build the WAR file using:
```sh
./gradlew build
```
Deploy the generated `.war` file to Tomcat's `webapps` directory.

## License

This project is open-source and available under the MIT License.

