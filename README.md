# Authentication API

This project is a backend API for authentication services, built using **Spring Boot** with **MongoDB** for data persistence and **Java Mail** for email handling. It includes the following features:

- **User Registration** (`/register`)
- **User Login** (`/login`)
- **Forgot Password** (`/forgot-password`)

---

## Built With

- **Spring Boot**: Framework for building Java applications.
- **MongoDB**: NoSQL database for storing user data.
- **Java Mail**: For sending email notifications.
- **Spring Security**: To handle authentication and authorization.

---

## Getting Started

### Prerequisites

To run this application, ensure you have the following installed:

- **Java 17 or higher**: [Download here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven**: [Install Maven](https://maven.apache.org/install.html)
- **MongoDB**: [Install MongoDB](https://www.mongodb.com/try/download/community)

---

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/hackintoshsa/auth-api.git
   cd auth-api
   ```

2. Configure application properties:
   Update the `src/main/resources/application.properties` or use environment variables for sensitive data (e.g., database URL, email credentials).

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

### API Endpoints

| Method | Path              | Description                |
|--------|-------------------|----------------------------|
| POST   | `/register`       | Register a new user.       |
| POST   | `/login`          | Authenticate a user.       |
| POST   | `/forgot-password` | Send password reset link. |

---

## Reference Documentation

For additional help and reference, check the following resources:

- [Official Apache Maven Documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#data.nosql.mongodb)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Java Mail Documentation](https://javaee.github.io/javamail/)

---

### Docker Compose

The project includes a `compose.yaml` file for running MongoDB locally using Docker. To use it:

1. Install Docker: [Docker Installation Guide](https://docs.docker.com/get-docker/)
2. Start MongoDB with Docker Compose:
   ```bash
   docker-compose up -d
   ```

---

## Future Enhancements

- Add user roles (Admin/User).
- Implement OAuth2 for third-party authentication.
- Add API rate limiting for enhanced security.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Author

**Benjamin Ndumiso Modimokwane**
- GitHub: [https://github.com/hackintoshsa](https://github.com/hackintoshsa)
