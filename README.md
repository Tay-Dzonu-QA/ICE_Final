<img src = "src\main\resources\img\Choonz.jpg" width ="300">

# Music Web App: Choonz
This project set out to create a rigorously tested, functional music library to organise playlists. The aim of this project was to be achieved in a team of 4 by satifying all the requirements to meet the MVP as well being agile to changing 'client' requirements. This project was completed over two 5-day Sprints. The technologies used in this project include:
*	Version Control System: Git
*	Source Code Management: GitHub
*	Kanban Board: Jira
*	Back-End Programming Language: Java
*	API Development Platform: Spring 
*	Front-End Web Technologies: HTML, CSS, JavaScript
*	Build Tool: Maven
*	Testing: JUnit, Mockito, Selenium, Gherkin, Cucumber, Jmeter
*	Static Analysis: SonarQube

## Table of Contents

1. [About the Project](#about-the-project)
1. [Testing](#testing)
1. [Getting Started](#getting-started)
    1. [Dependencies](#dependencies)
    1. [Features](#features)
    1. [Building](#building)
1. [License](#license)
1. [Authors](#authors)
1. [Acknowledgements](#acknowledgements)

## About the Project

##### Objective:

To improve upon, and provide a full test suite for, a full-stack Web application suitable for a given client specification – with utilisation of supporting tools, methodologies and technologies that encapsulate all modules covered during training.


##### The MVP had the following requirements:

* Full commitment to an Agile approach throughout the project, including daily stand-up meetings and utilisation of Sprints, user stories, acceptance criteria, story points, and effective communication within the team.

* A Kanban board with full expansion on user stories and tasks needed to complete the project. Providing a record of any issues or risks that were faced during the course of the project.

* Code fully integrated from every team member into a central repository on a Version Control System, with utilisation of issues, pull requests, merge requests, and any other aspects.

* Fully designed test suites – unit, integration, user-acceptance, functional, and non-functional – for the improved website. Striving to achieve industry-standard (80%) test coverage through both unit and integration testing.

* The application should be spike-, load-, stress-, and soak-tested

* Periodically running the codebase through a static analysis tool,SonarQube, with relevant refactoring of the code accordingly to reduce code smells, bugs, and vulnerabilities.

* The final deliverable for this project is the completed application with full documentation around utilisation of supporting tools. This will require a fully functional application.


Kanban Board for Project: [Jira](https://tdzonu-qa.atlassian.net/secure/RapidBoard.jspa?rapidView=4&projectKey=IF&view=planning&selectedIssue=IF-14&epics=visible&issueLimit=100&selectedEpic=IF-4)


## Testing

#### Test Coverage
A total of 538 tests were written (175 Selenium, 60 Integration (Mockito), 303 Unit (JUnit), resulting in 97% coverage.

<summary>Screenshots</summary>
<img src = "src\main\resources\img\Coverage.png">

#### Unit Tests 
A total of 303 Unit tests were written for domains, DTOs, services and controllers.
JUnit is used for unit tests which will test individual methods within a class for functionality.

#### Integration Tests 
A total of 60 Integration tests were written for both controllers & Service classes.
Mockito is used for intergration testing which test how different classes interact with each other by 'mocking' methods & classes. This means they can be tested for intergration by assuming the methods & classes it relies on are fully functional.
The change of the one to many and many to one relationships in the Playlist and Track classes caused difficulty with this as the test classes needed to be initialized in a different way but was ultimately successful.

#### User Acceptance Tests (with Selenium, Cucumber and Gherkin)
175 Selenium tests were written. These were written on two different computers so in order to run them you would need to make sure the driver is pointing towards the correct area on your local computer and each test needs to have the correct port that you are hosting the web app on.

The selenium, Cucumber and Gherkin tests can be found in `src/test/java/com/qa/test/Cuke` and `src/test/resources/Cuke`

#### Non-functional Testing (with Jmeter)
A total of four main non-functional tests have been performed to measure the system's capacity, responsiveness and stability.

A Stress test has been performed to establish the limits of the system under extreme load. This has been achieved by increasing the number of concurrent actions overtime in the system.

A Load test has been performed to observe the system's behaviour under a specific load. this has been achieved by carrying out a number of concurrent tasks for a certain amount of time.

A Soak test has been performed to analyse the perfomance of the system under expected conditions. This has been achieved by reducing the number of users and time from previous tests.

A Spike test has been performed to understand how a system would behave under a sudden increase or decrease of load in the system. This has been achieved by rapidly increasing the workload at certain points, to then drastically reduce the workload.

Details of these tests can be found below


<details>
<summary>Screenshots</summary>
    
##### Stress Test Setup:
<img src = "src\main\resources\img\StressTestSetup.PNG">

##### Stress Test Results:
<img src = "src\main\resources\img\StressTest.png">


##### Load Test Setup:
<img src = "src\main\resources\img\LoadTestSetup.PNG">

##### Load Test Results:
<img src = "src\main\resources\img\LoadTest.png">


##### Soak Test Setup:
<img src = "src\main\resources\img\SoakTestSetup.PNG">

##### Soak Test Results:
<img src = "src\main\resources\img\SoakTest.png">


##### Spike Test Setup:
<img src = "src\main\resources\img\SpikeTestSetup.PNG">

##### Spike Test Results:
<img src = "src\main\resources\img\SpikeTest.png">
</details>

#### Static Analysis

SonarQube for static analysis.
<details>
<summary>SonarQube</summary>
<img src = "src\main\resources\img\SonarQube.png">
</details>

## Getting Started

### Dependencies
What things you need to install the software and where to find them.

**To Run**

Java SE 8 (or later) to run the jar file.
Maven to create the jar-file and run.

Most desktop and mobile browsers are suitable for viewing the front end.
```
Eclipse IDE was used for this project.
Postman was used to test API calls from the H2 before implementing them within the project.
```

<details>
<summary>Links for Dependencies</summary>
    
<p>Below are a list of dependencies used within the project.</p>

* [Java SE 14](https://www.oracle.com/java/technologies/javase-downloads.html#JDK14)
* [Apache Maven](https://maven.apache.org)
* [Git](https://git-scm.com/downloads)
* [Eclipse IDE](https://www.eclipse.org/downloads)
* [Postman](https://www.postman.com/downloads)
* [Google Chrome](https://www.google.com/chrome)
</details>

### Getting the Source

This project is hosted on [GitHub](https://github.com/Tay-Dzonu-QA/ICE_Final). You can clone this project directly using this command in the Command Prompt:

```
git clone https://github.com/Tay-Dzonu-QA/ICE_Final
```
**[Back to top](#table-of-contents)**

## Features

### Login Page

<details>
<summary>Description</summary>

Login page:    
* Simple
* allows a user to create account or login using their details
* also has links to other areas of the site but with limited capability without logging in

</details>

<details>
<summary>Screenshot</summary>
<img src = "src\main\resources\img\LoginPage.png">
</details>

### Playlist HomePage

<details>
<summary>Description</summary>
    
Playlist Home page:
* Offers the user an area to manage playlists
* Album artwork at the bottom.
* Add Playlist button to add and customize playlists
* Navigation to get to other areas of the site on navbar and icons on each playlist card

</details>

<details>
<summary>Screenshot</summary>
<img src = "src\main\resources\img\PlaylistPage.png">
</details>

### Album, Artist, Genre, Track Pages

<details>
<summary>Description</summary>
    
The Album, Artist, Genre, Track pages: 
* Offer an area to see each of these entities in full and have crud functionality
* Has sidebar to either Sort, Filter or Order the data 

</details>

<details>
<summary>Screenshot</summary>
<img src = "src\main\resources\img\EntityPage.png">
</details>

## Building

How to build the project: 

### Built With

[Maven](https://maven.apache.org/) - Dependency Management

* Clone the repo to your machine.
* Open the cmd line / git bash inside the repo file directory.
* Run the following commands:

``` mvn clean package ```

``` mvn spring-boot:run ```

Note: After launching the Spring boot application you can then navigate to `localhost:8082` via a browser to reach the home page of the web interface. The app will run until you trigger the `/shutdownAppContext` API call.

Another way of running the programme is by cloning the repo, opening the build in as a maven project in your IDE and running as a Spring boot app. Following this open the static folders in Visual studio code (or equivilant) and open with a live server.

**[Back to top](#table-of-contents)**
## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Authors

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- [**Owen Jackson**](https://github.com/OJackson1)
- [**Tay Dzonu**](https://github.com/Tay-Dzonu-QA)
- [**Mohammad Rashid**](https://github.com/MRashid98)
- [**Oforitsesan Towesho**](https://github.com/CodewithOsatQA)

## Acknowledgements

Thanks to all at QA for your help and thanks to [**Nick Johnson**](https://github.com/nickrstewarttds) for your continued support over the past 12 weeks.
