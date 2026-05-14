# eCommerce Spring Boot Application

This project is a modular eCommerce backend application built using Java 23 and Spring Boot 4. It follows a feature-based modular architecture to ensure scalability and maintainability.

## 🚀 Technologies Used

- **Java 23**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **H2 Database** (In-memory for development)
- **Lombok** (Boilerplate reduction)
- **MapStruct** (Object mapping)
- **Jakarta Validation** (Input validation)
- **Maven** (Build tool)

## 📁 Project Structure

The project follows a modular structure under `src/main/java/com/atrdev/ecomapp/modules/`:

- `user`: User management (Profiles, Roles, Addresses).
- `product`: Product catalog management.
- `cart`: Shopping cart functionality.
- `order`: Order processing and history.
- `shared`: Cross-cutting concerns like Exception Handling and Utilities.

## 🛠️ Getting Started

### Prerequisites

- JDK 23
- Maven 3.9+

### Installation & Run

1. Clone the repository.
2. Navigate to the `ecom-app` directory:
   ```bash
   cd ecom-app
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

### H2 Database Console

You can access the in-memory database console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:test`
- **User**: `sa`
- **Password**: (empty)

## 🛣️ API Endpoints (Selection)

### Products
- `GET /api/products` - List all products
- `POST /api/products` - Create a new product
- `GET /api/products/search?keyword=...` - Search products
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Remove product

### Users
- `GET /api/users` - List all users
- `POST /api/users` - Register a new user
- `GET /api/users/{userId}` - Get user details
- `PUT /api/users/{id}` - Update user profile

### Cart
- `GET /api/cart` - View current user's cart (Requires `X-User-ID` header)
- `POST /api/cart` - Add item to cart (Requires `X-User-ID` header)
- `DELETE /api/cart/items/{productId}` - Remove item from cart (Requires `X-User-ID` header)

### Orders
- `POST /api/orders` - Create a new order from cart (Requires `X-User-ID` header)

### 📈 Future Enhancements (In Progress)

- **Order Module**: Complete implementation of order processing, payment integration, and shipping status.
- **Security**: Implement JWT-based authentication and authorization.
- **Search**: Advanced filtering and pagination for product catalog.

## 🧩 Architectural Highlights

- **Exception Handling**: Centralized global exception handler in `shared/exception`.
- **Data Transfer Objects (DTOs)**: Used for request and response payloads to decouple internal entities from the API.
- **Mappers**: MapStruct is used for efficient and type-safe conversion between Entities and DTOs.
- **Validation**: Jakarta Validation constraints are applied to DTOs.
