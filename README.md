# Bookmark Application


### Technologies:

- Spring-boot
- JPA
- Hibernate
- H2 Database
- JUnit
- Maven


### Prerequisites Installation:

- Java8
- Maven3

### Database Setup:
- MySQL Database: Create database `bookmarkdb` and set username and password at src/main/resources/application.properties file as bellow:
		
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

		
