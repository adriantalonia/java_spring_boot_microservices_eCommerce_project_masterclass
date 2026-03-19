```
ecommerce/
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/
│   │   │   ├── EcommerceApplication.java
│   │   │   │
│   │   │   ├── config/                         # App-wide configuration
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   ├── CorsConfig.java
│   │   │   │   └── CacheConfig.java
│   │   │   │
│   │   │   ├── modules/                        # Feature modules (core structure)
│   │   │   │   ├── user/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   └── UserController.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── UserService.java        (interface)
│   │   │   │   │   │   └── UserServiceImpl.java
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   └── UserRepository.java
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   └── User.java
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   ├── request/
│   │   │   │   │   │   │   ├── CreateUserRequest.java
│   │   │   │   │   │   │   └── UpdateUserRequest.java
│   │   │   │   │   │   └── response/
│   │   │   │   │   │       └── UserResponse.java
│   │   │   │   │   ├── mapper/
│   │   │   │   │   │   └── UserMapper.java
│   │   │   │   │   └── enums/
│   │   │   │   │       └── UserRole.java
│   │   │   │   │
│   │   │   │   ├── product/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── service/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── entity/
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   ├── request/
│   │   │   │   │   │   └── response/
│   │   │   │   │   └── mapper/
│   │   │   │   │
│   │   │   │   ├── order/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── service/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── Order.java
│   │   │   │   │   │   └── OrderItem.java
│   │   │   │   │   ├── dto/
│   │   │   │   │   └── mapper/
│   │   │   │   │
│   │   │   │   ├── cart/
│   │   │   │   ├── payment/
│   │   │   │   ├── category/
│   │   │   │   └── inventory/
│   │   │   │
│   │   │   ├── shared/                         # Cross-cutting concerns
│   │   │   │   ├── exception/
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   ├── BusinessException.java
│   │   │   │   │   └── ErrorResponse.java
│   │   │   │   ├── util/
│   │   │   │   │   ├── DateUtils.java
│   │   │   │   │   ├── SlugUtils.java
│   │   │   │   │   └── PaginationUtils.java
│   │   │   │   ├── constants/
│   │   │   │   │   └── AppConstants.java
│   │   │   │   ├── validation/
│   │   │   │   │   └── UniqueEmailValidator.java
│   │   │   │   └── audit/
│   │   │   │       └── AuditableEntity.java    (base entity with createdAt/updatedAt)
│   │   │   │
│   │   │   └── security/                       # Auth & Security layer
│   │   │       ├── jwt/
│   │   │       │   ├── JwtTokenProvider.java
│   │   │       │   └── JwtAuthFilter.java
│   │   │       ├── service/
│   │   │       │   └── AuthService.java
│   │   │       └── controller/
│   │   │           └── AuthController.java
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       └── db/migration/                   # Flyway migrations
│   │           ├── V1__create_users.sql
│   │           └── V2__create_products.sql
│   │
│   └── test/
│       └── java/com/ecommerce/
│           └── modules/
│               ├── user/
│               │   ├── UserServiceTest.java
│               │   └── UserControllerTest.java
│               └── order/
│                   └── OrderServiceTest.java

```