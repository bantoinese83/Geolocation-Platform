# Geolocation Platform

Geolocation Platform is a Spring Boot application that provides geocoding services using the Nominatim API. It includes a web interface for searching locations and displaying them on a map.

## Features

- Geocoding search using Nominatim API
- Interactive map with Leaflet.js
- RESTful API for geocoding
- Swagger API documentation

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Hibernate Validator
- Leaflet.js
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/bantoinese83/geolocation-platform.git
    cd geolocation-platform
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

4. Open your browser and navigate to `http://localhost:8080` to access the web interface.

## Configuration

### Application Properties

The application can be configured using the `src/main/resources/application.properties` file. Here are some key configurations:

```ini
# Server configuration
server.port=8080

# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:geolocationdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

# Disable spring.jpa.open-in-view to prevent database queries during view rendering
spring.jpa.open-in-view=false

# Swagger configuration
swagger.api.title=Geolocation Platform API
swagger.api.description=API documentation for Geolocation Platform
swagger.api.version=1.0




```

### API Documentation

The Swagger API documentation can be accessed at `http://localhost:8080/swagger-ui.html`.

## Usage

### Web Interface

The web interface provides a search bar for geocoding locations and a map for displaying the results. Enter a location in the search bar and click the search button to geocode the location. The map will display the location with a marker.