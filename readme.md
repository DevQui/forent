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

## Schema/ERD
![Forent_ERD](https://github.com/DevQui/forent/blob/feature1/forent_database_erd.png?raw=true)


## About the API
This Spring Boot API is using Keycloak for restring specific users to access what resources, how they can access it, and what they can store in the MySQL Database.

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



## Requirements
- JDK/JRE
- [Ubuntu Docker](https://docs.docker.com/engine/install/ubuntu/) or [Windows Docker](https://docs.docker.com/docker-for-windows/install/)
- [Keycloak](https://www.keycloak.org/downloads.html)
- MySQL
- Jenkins
- NodeJs with NPM

## Installation
**Install Newman**  to run the Postman collection by copying the following command to your terminal

```bash
 npm install -g newman
```

**Install newman-reporter-htmlextra** to view the result of the postman collection in an HTML Report
```bash
 npm install -g newman-reporter-extra
```

**Install Keycloak** using the Standalone server distribution
- If using **zip** extract the file and navigate to where you extracted Keycloak and run in the terminal
```bash
ex: cd /Downloads/keycloak-11.0.3/bin
    ./standalone.sh
```
- If using **tar.gz**, follow this [link](https://medium.com/@hasnat.saeed/setup-keycloak-server-on-ubuntu-18-04-ed8c7c79a2d9)

After installation, you can copy this to your web browser to access Keycloak
```bash
localhost: 8080
```

**Keycloak Configuration** 
```bash
1. Add Keycloak admin username and password
2. Add new Realm "forentkeycloak"
3. Add client "forent"
```

**Install MySQL** to be able to access the database
Personally, I'm using Workbench for my database.
```bash
Connection Name: Rentals
Hostname: the_default_ip_address_or_change_to_localhost
Username: root
Password: your_password_here_or_if_you_prefer_leave_this_empty
```

If you modify any of the configurations, make sure you modify the "application.properties" in the src>main>resources
- Import the "forent_db.sql" file found in the mysql folder.

## Docker Hub
[Docker Hub Repository](https://hub.docker.com/repository/docker/dquisido/forent)

## Swagger Hub
[Swagger Hub Documentation](https://app.swaggerhub.com/apis/DevQui/ForRentAPI/1.0.0)
