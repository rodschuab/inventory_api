# Inventory API

API REST desenvolvida em **Java + Spring Boot + PostgreSQL** para gerenciamento de:

- Products
- Raw Materials
- Associação Product + Raw Material 
- Consulta de capacidade de produção baseada no estoque

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- Lombok
- PostgreSQL
- Springdoc OpenAPI (Swagger)

---

## Requisitos atendidos

- RF001 – CRUD de produtos   
- RF002 – CRUD de matérias-primas   
- RF003 – CRUD da associação produto ↔ matéria-prima   
- RF004 – Consulta de produtos possíveis com base no estoque  

---

## Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL

---

## Configuração do banco

- Variáveis de Ambiente

Configure no IntelliJ ou no sistema:

DB_URL=jdbc:postgresql://localhost:5432/inventory_db

DB_USERNAME=postgres

DB_PASSWORD=postgres

- Exemplo:

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Crie o banco:

```sql
CREATE DATABASE inventory_db;
```
## Como executar: 
- Na raiz do projeto: mvn clean spring-boot:run
- API Disponível: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
