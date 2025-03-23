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
git clone https://github.com/your-username/your-repo.git
```
### 2. Navigate to your Project Directory

After cloning the repository, navigate to the project directory:

```bash
cd your-repo
```
### 3.(Optional) Setup PostgreSQL Database Using Docker

The project includes a `docker-compose.yml` file for spinning up a PostgreSQL container. To start the database container, run the following command:

```bash
docker-compose up -d
```

### 5. Build and Test the Project

To build the project and install dependencies, run:

```bash
mvn clean install
```
### 6. Running Tests

To run the unit tests for the project, use the following command:

```bash
mvn test
```
### 7.(Optinal) Run The Application

Once the project is built, you can optionally run the application using:

```bash
mvn spring-boot:run
```