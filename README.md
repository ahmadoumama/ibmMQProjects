Prerequisites
Java 17+: Ensure Java is installed on your machine.

Maven: For building and managing the Spring Boot project.

Node.js: For managing the Angular project.

Docker and Docker Compose: For containerizing and orchestrating the services.

Step 1: Clone the Repository
bash
git clone https://github.com/ahmadoumama/ibmMQProjects.git
cd yourproject
Step 2: Environment Configuration
Edit the application.yml and docker-compose.yml files with appropriate configurations for your environment.

Step 3: Launch Services with Docker Compose
bash
docker-compose up -d
This will start the MySQL, Spring Boot, and Angular services.

Step 4: Access the Application
Back-end (Spring Boot): Accessible at http://localhost:8080

Front-end (Angular): Accessible at http://localhost:4200

Step 5: Test Key Functions
Messages:

Access the message list from the main menu.

Click on a message to see its details in a pop-up.

Partners:

Access the partner list from the menu.

Add a new partner via the form.

Delete an existing partner.

Step 6: Security Verification
Ensure authentication and authorization work as expected.
