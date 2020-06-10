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
- I have used H2 file based database for development purpose so no configuration change required.

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

		
