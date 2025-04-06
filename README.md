# Flight Booking System – Team F

This project is a web-based flight booking system developed for the **Software Engineering Project (HBV401G)** course. The system allows users to register, search for flights, book available seats, and view their personal bookings. It uses **Spring Boot**, **SQLite**, and **Thymeleaf**, and adheres to the MVC design pattern.

---

## Tech Stack

- **Backend**: Spring Boot, Spring MVC, Spring Data JPA (Java 17)
- **Frontend**: Thymeleaf templates, Bootstrap 5
- **Database**: SQLite (Persistent Storage)
- **Build Tool**: Maven

---

## Features Implemented

### User Management
- Register with a unique username
- Login with session-based authentication
- Logout and session invalidation
- Redirect to login on session expiration or unauthorized access

### Flight Search & Booking
- Search for flights by origin and destination
- View flight details on a separate page
- Book a flight if seats are available
- Booking confirmation view

### Booking History
- View all past bookings in `My Bookings` page
- Display booking time, origin, destination, flight number, departure, and number of seats booked

### Frontend UI
- Responsive design using Bootstrap 5
- Modular HTML with Thymeleaf fragments for:
  - Navigation (`fragments/nav.html`)
  - Footer (`fragments/footer.html`)
  - Page header (`fragments/pageheader.html`)
- Clear flow: Starts on login, register redirects to login, login redirects to index

### Code Quality & Documentation
- MVC architecture: Controllers, Services, Repositories, Entities
- JavaDocs for all backend classes and methods
- Clear separation of concerns and use of dependency injection
- Deprecated Thymeleaf syntax updated to use `~{...}` style

---

## Project Structure

```
├── src
│   └── main
│       ├── java/com/teamf/controller/    # All controllers
│       ├── java/com/teamf/service/       # Business logic services
│       ├── java/com/teamf/repository/    # JPA repositories
│       ├── java/com/teamf/entity/        # Entity classes: User, Flight, Booking
│       └── resources
│           ├── templates/                # Thymeleaf templates
│           │   ├── fragments/            # nav, footer, header
│           │   ├── index.html            # Search page
│           │   ├── search_results.html   # Search results
│           │   ├── flight_details.html   # Flight details
│           │   ├── confirmation.html     # Booking confirmation
│           │   ├── bookings.html         # My Bookings page
│           │   ├── login.html            # Login
│           │   └── register.html         # Register
│           └── application.properties    # DB config (SQLite)
├── pom.xml                               # Maven build file
├── teamf.db                              # SQLite database (generated)
└── README.md                             # This file
```

---

## Running the Project

1. Ensure Java 17 and Maven are installed.
2. Run: `mvn clean spring-boot:run`
3. Access the site at `http://localhost:8080`
4. Start at `/login`, register or log in to begin

---

## Authors

- Ingólfur Bjarni Elíasson and Team F

---