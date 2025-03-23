## Project Documentation

## Prerequisites

- **Java 21 SDK**: Ensure you have Java 21 installed on your system.  
  [Download Java 21 SDK](https://www.oracle.com/java/technologies/downloads/#java21)

- **Maven**: For building and running the project.  
  [Maven Installation Guide](https://maven.apache.org/install.html)

- **Docker**: For running a local PostgreSQL database container.  
  [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Setup Instructions

### 1. Clone the Repository

Start by cloning the GitHub repository to your local machine:

```bash
git clone https://github.com/eyalav12/tdp.git
```
### 2. Navigate to your Project Directory

After cloning the repository, navigate to the project directory:

```bash
cd tdp
```
### 3.(Optional) Setup PostgreSQL Database Using Docker

The project includes a `docker-compose.yml` file for spinning up a PostgreSQL container. To start the database container, run the following command:

```bash
docker-compose up -d
```

### 4. Build and Test the Project

To build the project and install dependencies, run:

```bash
mvn clean install
```

This will clean, build, and install the project, including running tests as part of the build process.
### 5. Running Tests

To run only the unit tests, use:
```bash
mvn test
```
### 6.(Optinal) Run The Application

Once the project is built, you can optionally run the application using:

```bash
mvn spring-boot:run
```