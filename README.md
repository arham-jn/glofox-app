# Glofox Booking System

This is a simple booking system API for managing class bookings.

## Getting Started

Follow these instructions to set up the project on your local machine.

### Prerequisites

1. Make sure you have Git installed.
2. Install Java Development Kit (JDK) version 17 (or the required version).
3. Install any other dependencies or tools, as specified.

### Clone the Repository

1. Open a terminal or command prompt.
2. Run the following command to clone the repository:

	git clone https://github.com/arham-jn/glofox-app.git
   
3. Navigate to the project directory:

	cd glofox-app
	
### Testing the APIs
You can use Postman or any other API testing tool to interact with the endpoints. For example:

	POST /classes: Adds a new class

	POST /bookings: Creates a new booking

### Troubleshooting
If the application doesn't start due to port issues, ensure no other process is using port 8081. You can modify the port in the application.properties if needed.

## Features
- Create a booking.
- Validate booking inputs.
- Handle responses and errors.

## Architecture
The system is built using a multi-layered architecture where each component is responsible for a specific functionality. The system has been designed to follow the SOLID principles, ensuring maintainability and scalability.

#### Core Design Principles:
- Separation of Concerns: Each layer focuses on a specific responsibility, and they are abstracted from each other.
- Interface-Based Design: Services, repositories, and validation logic are all abstracted using interfaces to decouple implementations from usage.
- Dependency Injection: Constructor-based dependency injection is used to inject dependencies into classes, avoiding field injection and ensuring testability.

## Technologies
- Java
- Spring Boot: For creating RESTful APIs.
- JUnit 5: For unit testing and test-driven development.
- Mockito: For mocking dependencies in unit tests.
- Maven: For project management and dependencies.

## Layers and Abstractions
### Controller Layer
The Controller Layer is responsible for handling HTTP requests and returning appropriate responses. It ensures that the business logic is kept separate from request handling.

	Controller: BookingsController handles /bookings API endpoints.
				  ClassesController handles /classes API endpoints
Responsibilities:
- Receives HTTP requests.
- Handles validation and delegates business logic to services.
- Returns standardized responses using ResponseEntityBuilder.

### Service Layer
The Service Layer implements business logic and coordinates operations between different components (validation, repository).

	Service: BookingServiceImpl and ClassesServiceImpl
Responsibilities:
- Processes the booking logic.
- Interacts with validaltion layer to validate inputs
- Interacts with the repository layer to persist data.
- Constructs response objects.


#### Repository Layer
The Repository Layer abstracts the data access logic. It interacts with in-memory data storage or a database (in the case of a more advanced implementation).

	Repository: InMemoryClassesRepository and InMemoryBookingRepository
Responsibilities:
- Handles CRUD operations for BookingDTO objects.
- Provides methods for saving and querying bookings.


### Validation Layer
The Validation Layer is responsible for ensuring that the data provided by the user is valid before saving it to the database.

	Validation Service: ClassValidationServiceImpl BookingValidationServiceImpl
Responsibilities:
- Validates booking details.
- Returns validation errors if any rules are violated.

### DTOs (Data Transfer Objects)
DTOs are used for transferring data between the client and the server. They ensure that the data structure is clean and easy to use.

	BookingDTO: Used to capture booking information from the client.
	BookingResponseDTO: Used to format and send the response after booking confirmation.
	ClassesDTO: Used to capture classes information from the studio owner.
	ClassesResponseDTO: Used to format and send the response after creating classes confirmation.
	ErrorResponseDTO: Used to format error responses
	
### Response Entity Builder
The ResponseEntityBuilder provides standardized responses for API requests.

	ResponseEntityBuilder: Contains methods like successResponseBuilder and errorResponseBuilder to generate consistent success and error responses.