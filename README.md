#  Hotel Management System

> A Java-based desktop application designed to simplify and organize core hotel management operations, including guest management, room management, hotel information, and reservation handling.


##  Overview

The **Hotel Management System** provides a structured graphical user interface (GUI) for managing hotel-related information efficiently. The application follows a modular project structure with separate components for the user interface, data models, services, and database connectivity.

---

##  Key Features

- Guest Management
- Hotel Management
- Room Management
- Reservation Management
- Retrieve and Display Data
- Database Connectivity
- Graphical User Interface (GUI)
- Modular Project Structure


##  Technologies Used

| Technology       | Purpose                             |
|------------------|-------------------------------------|
| **Java**         | Application development             |
| **Java Swing**   | Graphical User Interface            |
| **MySQL**        | Database management                 |
| **JDBC**         | Database connectivity               |
| **Eclipse IDE**  | Development environment             |
| **Git & GitHub** | Version control and project hosting |


##  Project Architecture

Hotel-Management-System/
│
├── src/
│   ├── gui/
│   │   ├── GuestPanel.java
│   │   ├── HotelPanel.java
│   │   ├── MainFrame.java
│   │   ├── ReservationPanel.java
│   │   ├── RetrieveDataPanel.java
│   │   └── RoomPanel.java
│   │
│   ├── model/
│   │   ├── Guest.java
│   │   ├── Hotel.java
│   │   ├── Reservation.java
│   │   └── Room.java
│   │
│   ├── service/
│   │   ├── dao/
│   │   └── ReservationService.java
│   │
│   └── util/
│       └── DatabaseConnector.java
│
├── .gitignore
├── .classpath
├── .project
└── README.md


## Application Modules

### Guest Management

Manages guest-related information and provides functionality for storing and retrieving guest data.

### Hotel Management

Handles hotel information and related operations.

### Room Management

Provides functionality to manage room-related information and availability.

### Reservation Management

Handles reservation-related operations and services.

### Data Retrieval

Allows stored information to be retrieved and displayed through the application.

## Database

The application uses MySQL as the database and JDBC for communication between the Java application and the
database. Database connectivity is handled through DatabaseConnector.java. Database configuration is required
before running the application.

## How to Run

• **Clone the Repository**: git clone https://github.com/muskanb548/Hotel-Management-System.git
• **Open the Project**: Open the project in Eclipse IDE.
• **Configure Database**: Install and start MySQL Server, create the required database, configure credentials in DatabaseConnector.java, and add the MySQL Connector/J JDBC driver.
• **Run the Application**: Run MainFrame.java.

## Learning Objectives

• Object-Oriented Programming (OOP)
• Java programming
• Java Swing GUI development
• Database connectivity using JDBC
• CRUD operations
• Modular software architecture
• Exception handling
• Git and GitHub version control

## Future Enhancements

• User authentication and role-based access
• Online room booking
• Payment and billing management
• Advanced search and filtering
• Report generation
• Improved UI/UX
• Email and notification integration

## Author
**Muskan Bharti**

**GitHub**: https://github.com/muskanb548
**Project Repository**: https://github.com/muskanb548/Hotel-Management-System

## Project Status

Completed — Academic Project

## License
This project is developed for educational and academic purposes
