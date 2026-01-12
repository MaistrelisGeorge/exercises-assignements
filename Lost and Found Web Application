# Lost and Found Web Application

Spring Boot web application for municipal lost and found services with role-based access control, search functionality, and analytics.

## Technologies

- **Framework**: Spring Boot 3.5.7
- **Language**: Java 17 (LTS)
- **Database**: MySQL 8.0
- **Build Tool**: Maven 3.x
- **Template Engine**: Thymeleaf 3.0

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.x

## Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE lostandfound;
```

2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lostandfound
spring.datasource.username=root
spring.datasource.password=R00t5Bl00dyR00t5!
server.port=8081
```

3. Run schema initialization:
```bash
mysql -u root -p lostandfound < src/main/resources/schema.sql
```

## Running the Application

```bash
mvn spring-boot:run
```

Application runs on: http://localhost:8081

## Test Accounts

**Admin:**
- Username: `admin`
- Password: `admin123`

**Member:**
- Username: `john`
- Password: `member123`

## Features

- User registration and authentication (BCrypt password hashing)
- Role-based access control (MEMBER/ADMIN)
- Lost/found item management (CRUD operations)
- Multi-criteria search (category, status, date range, location)
- Discussion boards on items
- Admin analytics dashboard
- PDF export functionality

## Project Structure

```
src/main/java/com/example/lostandfound/
├── controller/     # HTTP request handlers
├── service/        # Business logic
├── repository/     # Data access layer
├── entity/         # JPA entities
└── filter/         # Authentication filter

src/main/resources/
├── application.properties  # Configuration
├── schema.sql             # Database schema
└── templates/             # Thymeleaf views
```


**Student ID**: 2331873  
**Institution**: University of Greater Manchester
**Module**: Enterprise Systems Development
