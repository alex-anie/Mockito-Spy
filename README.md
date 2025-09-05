# Mockito SPY

### JobBoard Spring Boot Application

A **Spring Boot CRUD application** for managing employees and companies.

This project demonstrates:

- REST API development with Spring Boot
- Mockito (Mocks & Spies) for unit and integration testing

---

## 📑 Table of Contents

1. Requirements
2. Project Setup
3. Running the Application
4. API Endpoints
    - Employees
    - Companies
5. Database
6. Testing
    - Unit Tests
    - Integration Tests
    - Mockito Examples
7. Project Structure

---

## 🚀 Requirements

- Java 24
- Maven 3.8+
- Spring
- Mockito
- Junit
- IntelliJ IDEA Ultimate

---

## ⚙️ Project Setup

Clone the repository:

```bash
git clone https://github.com/alex-anie/Mockito-Spy/tree/main
cd jobboard

```

Build the project:

```bash
mvn clean install
```

---

## ▶️ Running the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The server will start at:

👉 `http://localhost:8080`

---

## 📡 API Endpoints

### 👩‍💻 Employees

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Add a new employee |
| PUT | `/api/employees/{id}` | Update existing employee |
| DELETE | `/api/employees/{id}` | Delete employee by ID |

**Employee Model**

```java
public class Employee {
    private Long id;
    private String name;
    private String role;
    private Double salary;
    private List<String> skills;
}

```

---

### 🏢 Companies

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/companies` | Get all companies |
| GET | `/api/companies/{id}` | Get company by ID |
| POST | `/api/companies` | Add a new company |
| PUT | `/api/companies/{id}` | Update existing company |
| DELETE | `/api/companies/{id}` | Delete company by ID |

**Companies Model**

```java
public class Companies {
    private Long id;
    private String name;
    private int numberOfEmployees;
    private List<String> industries;
}

```

---

## 🗄️ Database

Dummy data is preloaded on application startup.

---

## 🧪 Testing

**JUnit 5** + **Mockito** for testing.

### ✅ Unit Tests

Located in:

`src/test/java/com/alexanie/app`

- Example: `EmployeeControllerTest` tests controller logic with mocks.

```java
@Mock
private EmployeeService service;

@InjectMocks
private EmployeeController employeeController;

```

---

### 🌐 Integration Tests

Using `MockMvc` with `@SpringBootTest` + `@AutoConfigureMockMvc`.

```java
@SpyBean
private EmployeeService employeeService;

mockMvc.perform(get("/api/employees"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.length()").value(2));

```

---

### 🕵️ Mockito Examples

- **Mock** → fake object, no real methods called.
- **Spy** → wraps real object, real methods unless stubbed.

### Mock Example

```java
EmployeeService mockService = mock(EmployeeService.class);
when(mockService.getAllEmployees()).thenReturn(List.of(new Employee(...)));

```

### Spy Example

```java
EmployeeService spyService = spy(new EmployeeService());
doReturn(List.of(new Employee(...))).when(spyService).getAllEmployees();

```

---

## 📂 Project Structure

```
src/main/java/com/alexanie/app
 ├── controller/    # REST controllers
 ├── model/         # Entities (Employee, Companies)
 ├── repository/    # Spring Data JPA repositories
 ├── service/       # Service layer (business logic)
 └── AppApplication.java  # Main entry point

src/test/java/com/alexanie/app
 ├── company/    # Company Rest API tests
    ├── *.java 
 
 
 ├── employee/    # Employee Rest API tests
    ├── *.java
 
```