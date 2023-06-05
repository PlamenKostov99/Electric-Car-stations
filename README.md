# Electric Car Stations
# Introduction
This project is a web-based system designed to control electric vehicle charging points. It is developed as part of a Master's thesis in Information Technology.

# Technologies Used
Java: The main programming language used to provide the logic and functionality needed to control the charging points.
PostgreSQL: A powerful open-source relational database system used to manage and store data related to the charging stations and their usage.
SpringBoot: A framework that simplifies the setup and development of Spring applications. It is used to expedite the development process and create a stand-alone, production-grade application.
Thymeleaf: A Java-based library used to create web applications. It provides a way to integrate HTML templates with Spring MVC.
Flyway: An open-source database migration tool that helps to manage and apply changes to the database.
# Project Structure
The project consists of a developed prototype of a web application for controlling electric vehicle charging points. The architecture of the developed prototype, a database diagram, and the structured main components of the web system and the developed prototype form the basis of the project.

# Database Migrations
This project uses Flyway for managing database migrations. Before you can run the application, you'll need to set up your database schema. Here's how to do it:

Ensure that you have a PostgreSQL database set up and that the connection details in your application.properties file are correct.

Open a terminal window and navigate to the root directory of the project.

To run the database migrations, use the following command:

```bash
./gradlew flywayMigrate
```

This command will apply all the migrations in the src/main/resources/db/migration directory to your database.

Once the command completes, your database should be set up and ready to use with the application.
Please note that if you make any changes to the database schema, you should create a new Flyway migration to apply those changes. This ensures that your database schema stays in sync with the codebase.

If you encounter any issues while running the migrations, please check the Flyway documentation or open an issue on this GitHub repository.