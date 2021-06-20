Coverage: 84.3%

# (HWA) Hobby Web Application by Haaris Moghal

This is a Full stack web application which uses Java (backend), HTML, CSS, and JavaScript (frontend) that a user can interact with using a web browser (HTML pages).
It uses a MySQL database with the following entities/tables via a cloud platform instance: player and team.

## Table of contents

* [Prerequisites](https://github.com/HaarismoghalQA/HobbyProject#Prerequisites)
* [Installing](https://github.com/HaarismoghalQA/HobbyProject#Installing)
* [Deployment](https://github.com/HaarismoghalQA/HobbyProject#Deployment)
* [Testing](https://github.com/HaarismoghalQA/HobbyProject#Testing)
* [Jira-Integeration](https://github.com/HaarismoghalQA/HobbyProject#Integeration)
* [Creating-a-Jar-file](https://github.com/HaarismoghalQA/HobbyProject#Creating-a-Jar-file)
* [Built-With](https://github.com/HaarismoghalQA/HobbyProject#Built-With)
* [Versioning](https://github.com/HaarismoghalQA/HobbyProject#Versioning)
* [Authors](https://github.com/HaarismoghalQA/HobbyProject#Authors)
* [License](https://github.com/HaarismoghalQA/HobbyProject#License)
* [Acknowledgments](https://github.com/HaarismoghalQA/HobbyProject#Acknowledgments)


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites


* [Java JRE 14.0.1](https://www.oracle.com/uk/java/technologies/javase/jdk14-archive-downloads.html)
* [Spring or other IDE](https://spring.io/tools)
* [MySQL](https://www.mysql.com/downloads/)
* [Git Bash](https://git-scm.com/downloads)
* [Maven](https://maven.apache.org/)
* [GCP](https://cloud.google.com/)

### Installing

1. You will need to clone down the repo
2. On your local machine, do a Git Bash in the folder destination you'd like to clone to. 
3. Clone the repo to your local system using the command "Git clone" and the Repository URL 
4. Launch the project in your IDE of choice.
5. set up JDBCconnection so that it connects to your database

## Deployment

To deploy this software to a cloud database such as Google Cloud Platform (GCP) you will need to do the following;

### Open the project

1. In the main folder ```'src/main/resources' Open > application.properties``` 
2. Check to see if  ``` spring.profiles.active=prod ``` exists and set to prod as it is declaring your connection point
3. Also in the main folder open ```'src/main/resources' Open > application-prod.properties``` 
4. Scroll down until you see ``` spring.datasource.url= ``` declaring your connection point
5. Change the connection point to your desired Database location

## Testing

This application has been tested using JUnit testing. Mockito was used along with JUnit which allows you to create and configure mock objects. Using Mockito greatly simplifies the development of tests for classes with external dependencies.

### Running the tests

1. In the main folder ```'src/main/resources' Open > application.properties``` 
2. Check to see if  ``` spring.profiles.active=test ``` exists and set to prod as it is declaring your connection point
3. Also in the main folder open ```'src/main/resources' Open > application-test.properties``` 
4. Scroll down until you see ``` spring.datasource.url= ``` declaring your connection point
5. Change the connection point to your desired Database location
6. In the main folder ```'src/test/java'``` you can open each package to see each tests.
7. You can run them individually or run them all by right clicking the java file and click on run as then :```'run as JUnit test'```
8. Similarly you can choose to do a coverage test by right clicking the java file and click on Coverage as then :```'run as JUnit test'```
9. You can run all the test by right clicking the the whole project file and do a Coverage/Normal Test and click on run as then :```'run as JUnit test'```

## Integeration

Integrating jira and github together means that you are able to track the progress of completed work through jira via the use of smart commits.

Here is a link to my Jira board. [Link](https://haarismoghalims.atlassian.net/jira/software/projects/HOB/boards/2)

### Creating a Jar file

1. Choose or create a folder so that you can download the application.
2. Right click inside the folder.
3. Select Git Bash.
4. Write the following ```mvn clean package```
6. This will now run all of the applications testing, ensuring it all passes, you'll then get JAR file produced in the project target folder.

To deploy this software to a cloud database such as Google Cloud Platform (GCP) you will need to do the above;


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

* Version Control System: Git 
* Source Code Management: GitHub 
* Database Management System: MySQL Server 5.7 (local or GCP instance)
* [Maven Versioning](https://maven.apache.org/)

## Authors

* **Haaris Moghal** - *Completed the whole project from scratch* -[HaarisMoghalQA](https://github.com/HaarismoghalQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* I would like to thank *[Jordan Harrison](https://github.com/JHarry444) - QA trainer *[Alan Davis](https://github.com/MorickClive)  - QA trainer *[Edsel Tham](https://github.com/edseltham88)  - QA trainer

