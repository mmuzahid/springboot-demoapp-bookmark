# Bookmark Application


### Technologies:

- Spring-boot
- JPA
- Hibernate
- MySQL Database
- JUnit
- Maven


### Prerequisites Installation:

- Java8
- Maven3

### Database Setup:
- MySQL Database: Create a database e.g. `bookmarkdb` and set db url, username and password at src/main/resources/application.properties file as bellow:
		
		spring.datasource.url=jdbc:mysql://localhost:3306/bookmarkdb
		spring.datasource.username=root
		spring.datasource.password=root

### Install Lombok in your IDE (for development purpose):
- Go to lombok maven dependecy folder e.g. {Users_Home}\.m2\repository\org\projectlombok\lombok\1.18.12\
- Run lombok jar from command line: 
		
		java -jar lombok-1.18.12.jar
		 
- Install Lomobok from list of IDE
- Re-launch Eclipse

### Maven Run Command:

- Maven command to run application without running test

		mvn clean spring-boot:run

- Maven command to run application with test

		mvn clean test spring-boot:run

- Maven command to run test only

		mvn clean test


### Docker Setup:
		
[Spring Boot Ref](https://spring.io/guides/gs/spring-boot-docker/)

[Docker Command Ref](https://docs.docker.com/engine/reference/commandline/docker/)

- Generate `bookmar.jar` using Maven

		maven clean package

- Build Docker Image from `Dockerfile`

		docker build -t bookmark .

- Create and Start Docker Container

		docker run -p 8080:8080 bookmark
		
- Check application is running or not from docker container list
		
		docker ps

- Show application image in docker image list
	
		docker images


### Sample Login User

**Admin User:** admin    **Password:** passowrd

**User:** user    **Password:** passowrd

