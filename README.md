# PRODUCT-API
product-management-api

******************Product Management System API******************

   ----A Spring Boot REST API that allows CRUD operations on products, with validation, authentication, Swagger documentation, and unit tests.----


   CRUD operations on Product entity  
   Validation using notempty annotation  
   Global exception handling  
   Basic authentication using Spring Security  
   API documentation with Swagger (OpenAPI) -creating the swagger doc 
   Unit testing with JUnit & Mockito  
   Follows SOLID principles and clean design patterns (DI, Repository)

   *********Tech stack********
- Java 17
- Spring Boot
- Spring Data JPA
- mysql
- Spring Security
- Bean Validation
- Swagger UI
- JUnit + Mockito

************Endpoints************

http://localhost:8096/api/products with the get mapping get all the products
http://localhost:8096/api/products/{id}  with the get mapping find the product as per the id
http://localhost:8096/api/products  with post and providing the product details add the new product
http://localhost:8096/api/products/{id} with put mapping update the product
http://localhost:8096/api/products/{id} with delete mapping delete the product

