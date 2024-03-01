# Password Manager - Back

The Password Manager is an application developed in Java with the Spring Boot framework, aimed at providing a secure and efficient solution for storing and managing passwords. This application was built using updated knowledge in Java, Spring Boot, JUnit 5, Docker, Spring Security, and JWT (JSON Web Token) and Postgress with database  with a special focus on security practices, including encryption using Bcrypt for user management and standard hash encryption for password storage.

## Project Structure

The project follows the MVC (Model-View-Controller) pattern for organization and structuring of the code, divided into different layers:

- **Config**: Contains project configurations, such as Spring Security and JWT configurations.
- **Controller**: Responsible for receiving HTTP requests and delegating to the appropriate services.
- **Model**:
  - **DTO (Data Transfer Object)**: Objects used to transfer data between the presentation layer and the service layer.
  - **Entity**: Represents domain entities that are mapped to database tables.
  - **Repositories**: Interfaces defining database access operations.
  - **Service**: Contains business logic and data manipulation.
- **Security**: Contains security and encryption logic.

## Security Implementation

Security is a fundamental part of the Password Manager and has been implemented using various practices and technologies:

- **Spring Security**: Used to provide robust authentication and authorization for application endpoints.
- **JWT (JSON Web Token)**: Used for token-based authentication, allowing users to access endpoints securely.
- **Bcrypt**: Used to encrypt user passwords during registration and authentication processes, ensuring information security.
- **Hash for password storage**: User passwords are securely stored in the database using hash techniques to protect against data leaks.
- **Database Relationship**: Passwords maintain references to users to prevent overloading user endpoints during the login process and ensure data security.

## Endpoints

### AuthenticationController

This controller handles operations related to user authentication. It provides endpoints for login, token verification, and token updating.

### MyPasswordController

This controller is responsible for CRUD operations related to user passwords. All accesses and modifications are performed through JWT tokens, ensuring that only the authenticated user can access and manage their own passwords.

### UserController

The UserController manages operations related to user registration and management. It provides endpoints for registering new users and updating or deleting existing user information.

## Database Relationship Diagram

![image](https://github.com/EzequiasSoares1/PasswordManager-Back/assets/87997012/ca6342c5-13bb-475a-93c8-aecf1e140e54)


## Instructions to Run the Application

1. **Clone the Repository**: git clone https://github.com/EzequiasSoares1/PasswordManager-Back

2. **Access the Project Directory**: cd  PasswordManager-Back
   
3. **Run Docker Desktop**: Make sure you have Docker installed and running. Docker will start the database.

5. **Run the Application**: Run the Spring Boot application in your IDE or use Maven to build and run the application from the command line.

6. **Test the Endpoints**: The endpoints will be available at `http://localhost:8080`. You can use a tool like Postman or Insomnia to test the endpoints provided by the controllers.


