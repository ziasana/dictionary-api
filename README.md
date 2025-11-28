# 📘 Dictionary / Vocabulary API

A simple, fast, and cache-optimized **Spring Boot REST API** for
managing vocabulary words.\
Built to showcase clean architecture, caching, MongoDB persistence, and
modern API documentation.

## 🚀 Features

-   **CRUD operations** for vocabulary words\
-   **MongoDB** integration (Spring Data)\
-   **Caching with Caffeine** (`@Cacheable`, `@CacheEvict`)\
-   **Swagger / OpenAPI 3** documentation\
-   **Exception handling**\
-   Lightweight & production-ready structure\
-   Optional **Docker Compose** for MongoDB

## 🧱 Tech Stack

-   Java 17+
-   Spring Boot 3
-   Spring Web
-   Spring Data MongoDB
-   Spring Cache (Caffeine)
-   Swagger (springdoc-openapi)
-   Docker (optional)

  ## Requirements
- Java 21
- Maven 3.8+
- Optional: Docker (if needed)

## Build & Test
```bash
# Build the project
mvn clean install

# Run tests
mvn test

## 📂 Project Structure

    src/main/java/com/example/dictionary
     ├── controller/
     ├── service/
     ├── repository/
     ├── model/
     ├── exception/
     └── config/

## 📝 API Endpoints

### Get all words

GET /api/words

### Get a word (cached)

GET /api/words/{word}

### Create a new word

POST /api/words

### Update a word

PUT /api/words/{word}

### Delete a word

DELETE /api/words/{word}

## ⚡ Caching

``` java
@Cacheable(value = "word", key = "#word")
public Word findByWord(String word) { ... }

@CacheEvict(value = "word", key = "#word.word")
public Word update(Word word) { ... }
```

## 🔧 Configuration

    spring:
      data:
        mongodb:
          uri: mongodb://localhost:27017/dictionarydb

## 📘 Swagger UI

http://localhost:8080/swagger-ui.html

## 🐳 Docker (Optional)

    docker-compose up -d

## ▶️ Running the Application

    mvn spring-boot:run

## 🧪 Future Improvements

-   Redis cache\
-   JWT auth\
-   Pagination\
-   Tests\
-   CI pipeline

## 📄 License

MIT License
