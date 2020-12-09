# Forent API

Forent is a Spring Boot API that is for renting real estates and lets the users browse properties and book a schedule to rent a property. The users can be a host or a tenant where the host can add properties and the information that comes with it including location, and amenities. The tenant can save a property to their Favorites, request to book a schedule and the host can accept the booking.

## Resources
- Users
- Properties
- Schedules
- Locations
- Reviews
- Amenities
- Favorites

## About the API
This Spring Boot API is using Keycloak for restring specific users to access what resources, how they can access it, and what they can store in the MySQL Database.

### Http Requests Involved
- GET
- POST
- PATCH
- PUT
- DELETE

When running the Jenkins Pipeline which is used to build images to be pushed to the Docker Hub for you to pull to your machine on, Spring Boot, Keycloak and MySQL are all running in the background to create .jar file, execute the tests and be able to successfully build and push the image to Docker Hub.

## Technologies Involved  
- Java IDE
- Java
- Spring Boot
- Maven 
- MySQL
- MySQL Workbench
- Keycloak
- Postman
- Jmeter
- Docker
- Docker Compose
- Jenkins
- Newman
- NPM



## Installation
- Install the IDE of your choice
- Install JDK with JRE
- Install MySql
- Install Keycloak
