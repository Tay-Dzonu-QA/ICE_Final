<img src = "src/main/resources/static/img/Choonz.jpg" width ="300">

# Music Web App: Choonz
This project set out to create a rigorously tested, functional music library to organise playlists. The aim of this project was to be achieved in a team of 4 by satifying all the requirements to meet the MVP as well being agile to changing 'client' requirements. This task was to be completed in the course of two 5-day Sprints before it is scheduled to 'go live'. The technologies used in this project include:
*	Version Control System: Git
*	Source Code Management: GitHub
*	Kanban Board: Jira
*	Back-End Programming Language: Java
*	API Development Platform: Spring 
*	Front-End Web Technologies: HTML, CSS, JavaScript
*	Build Tool: Maven
*	Testing: JUnit, Mockito, Selenium
*	Static Analysis: SonarQube

## Table of Contents

1. [About the Project](#about-the-project)
1. [Project Status](#project-status)
1. [Getting Started](#getting-started)
    1. [Dependencies](#dependencies)
    1. [Features](#features)
    1. [Building](#building)
    1. [Usage](#usage)
1. [Further Reading](#further-reading)
1. [License](#license)
1. [Authors](#authors)
1. [Acknowledgements](#acknowledgements)

## About the Project

##### The overall objective of the project:

To create a full-stack Web application suitable for a given client specification, with utilisation of supporting tools, methodologies and technologies that encapsulate all modules covered during training.



##### The MVP was to be achieved with the following requirements:

* Full commitment to an Agile approach throughout the project, including daily stand-up meetings and utilisation of Sprints, user stories, acceptance criteria, story points, and effective communication within the team.

* A Kanban board with full expansion on user stories and tasks needed to complete the project. Providing a record of any issues or risks that were faced during the course of the project.

* Code fully integrated from every team member into a central repository on a Version Control System, with utilisation of issues, pull requests, merge requests, and any other aspects.

* Fully designed backend test suites for the application. Striving to achieve industry-standard (80%) test coverage through both unit and integration testing.

* Fully-designed frontend test suites for the application, which cover a wide range of user journeys.


<details>
<summary>Technologies Used</summary>
    
* Version Control System: Git
* Source Code Management: GitHub
* Kanban Board: Jira
* Back-End Programming Language: Java
* API Development Platform: Spring
* Front-End Web Technologies: HTML, CSS, JavaScript
* Build Tool: Maven
* Unit Testing: JUnit / TestNG, Mockito
* Integration Testing: Selenium
* Static Analysis: SonarQube

</details>

Kanban Board for Project: [Jira](https://hobby-site.atlassian.net/secure/RapidBoard.jspa?rapidView=2&projectKey=QCP&selectedIssue=QCP-60&atlOrigin=eyJpIjoiOTA4NmMxYzJlNzVkNGQ1NDg2MDc4YmYxODViNGQ2NzkiLCJwIjoiaiJ9)

Presentation on the project: [on google slides](???)


## Testing

#### Test Coverage
A total of 374 tests were written (68 Selenium, 37 Integration (Mockito), 269 Unit (JUnit), resulting in 86.2% coverage.

#### Unit Tests 
A total of 269 Unit tests were written for domains, DTOs, services and controllers.
JUnit is used for unit tests. A unit test will test individual methods within a class for functionality.

#### Integration Tests 
A total of 37 Integration tests were written for both controllers & Integration tests.
Mockito is used for intergration testing, to test how different classes interact with each other. Using 'mocking', methods & classes can be tested for inegration by assuming the methods & classes it relies on are fully functional.
Controllers and Services have associated Integration Tests.

#### User acceptance Tests (with Selenium)
68 Selenium tests were written, covering a large range of stories, excluding the payment (As the Swipe plugin was programmed to be exceptionally difficult to interact with using Selenium). Initially fluid waits and the page object model were used exclusively, working at fast pace. Due to this, API calls would be overwhelmed & blocked, requiring implementation of an extrinsic wait between test methods, slowing down testing drastically.

Extentreports were set up for each test, however most are currently unfinished.

The selenium tests can be found in `src/test/java/com/qa/test/selenium` within the project repository.

#### Static Analysis

SonarQube for static analysis.
<details>
<summary>SonarQube</summary>
<img src = "https://i.imgur.com/PoXvP3k.png">
</details>

## Getting Started

### Dependencies
What things you need to install the software and where to find them.

**To Run**

In the event of Google Cloud Platform App Engine not being live.
```
Java SE 8 (or later) to run the jar file.
Maven to create the jar-file and run.

In regards to veiwing from the front-end, most desktop and mobile browsers are suitable.
```
**To Develop**

All dependencie libraries are to be downloaded automatically, as instructed by the 'pom.xml'. 

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
* [Jenkins](https://jenkins.io/download)
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

### Home Page

<details>
<summary>Requirements</summary>
    
* is generally attractive.
* is the default for the entire site.
* allows users to navigate to other areas of the site
* is a good first impression and allows users to access the resources they require.

</details>

<details>
<summary>Screenshot</summary>
<img src = "https://i.imgur.com/mLGOaJF.jpg">
</details>

<details>
<summary>Description</summary>
The homepage features a visually appealing banner, as well as a section of tabs which the user can navigate to learn about the cinema. Featured movies are displayed in a gallery at the top, and coming movies are displayed at the bottom of the body of the page. It is the default for the site, and the user can navigate to other pages using the links present in the header or footer navigation bars, age-restriction icons or by clicking on a movie or its title itself.
</details>

<details>
<summary>Authors</summary>
    
* General Layout: **[Tawanda Siyamachira](https://github.com/TSiyamachiraQA)**,**[David Williams](https://github.com/DavidWilliamsQA)**
* Header: **[David Williams](https://github.com/DavidWilliamsQA)**
* Footer: **[Ashill Pathak](https://github.com/Ashillqa)**
* Movie tiles & links: **[Korbinian Ring](https://github.com/KMRRingQA)**
    
</details>

### Movie Gallery

<details>
<summary>Requirements</summary>
    
The listings gallery page:
* includes image gallery posters for the movies currently showing.
* is part of the overall site navigation.
* shows ensures each image appears on its own page.
* shows supporting text including Title, actors, director and showing times for each movie.

</details>

<details>
<summary>Screenshot</summary>
<img src = "https://i.imgur.com/xJbEAJD.jpg">
</details>

<details>
<summary>Description</summary>
The movie gallery retreives the list of movies with status "showing" and "featured" from the locally hosted (H2) database, and uses the stored API-IDs to retreive respective information from developers.themoviedb.org. Filters are functioning as well, allowing you to filter by genre, viewer rating, release year or a search term (which must be present in either description or title of the movie). A link to coming soon movies is at the bottom of the body, with similarly generated image tiles (status="coming" in this case). Clicking on the age-rating-icon links to the classifications page. Clicking an image or title redirects to the details page of the movie, which includes further movie details. Breadcrumbs can be used to navigate & reset canbe used to reset the filters.
</details>

<details>
<summary>Authors</summary>
    
*  **[Korbinian Ring](https://github.com/KMRRingQA)**
    
</details>

### New Releases Gallery

<details>
<summary>Requirements</summary>
    
The new releases gallery page: 
* includes an image gallery posters for forthcoming movies.
* is part of the overall site navigation
* Each movie appears on its own page
* Each movie has it's respective supporting text including Title, actors and director

</details>

<details>
<summary>Screenshot</summary>
<img src = "https://i.imgur.com/quX0ayq.jpg">
</details>

<details>
<summary>Description</summary>
The coming movies gallery possesses a similar set of features to the movie gallery. Filter terms are more limited as user ratings and release year are not required. The movies displayed here are status="coming".
</details>

<details>
<summary>Authors</summary>
    
*  **[Korbinian Ring](https://github.com/KMRRingQA)**
*  **[David Williams](https://github.com/DavidWilliamsQA)**
    
</details>

### Search bar

<details>
<summary>Requirements</summary>
    
The search bar:
* allows user to search by keyword (with the implementation of a placeholder)

</details>

<details>
<summary>Screenshot</summary>
<img src = "https://i.imgur.com/LuAWnwM.png">
<img src = "https://i.imgur.com/X7uJHBn.jpg">
</details>

<details>
<summary>Description</summary>
clicking the search icon on any page (in the header) will cause the search bar to appear. upon entering a search term and pressing "enter" or the search button, one will be forwareed to the search page with the term passed into the params. Additionaly, titles containing the search word will be displayed on the page. Filters function as intended, identically to the movie gallery. Pressit "reset" will clear all params and filters, displaying all movies currently in the databse. When no movies are returned in thes earch, the user will be informed. Below the search body, the user may redirect themselves to the movie gallery.
</details>

<details>
<summary>Authors</summary>
    
*  **[Korbinian Ring](https://github.com/KMRRingQA)**
    
</details>

### Movie Page

<details>
<summary>Requirements</summary>
    
Movies should:
* appear on their own page.
* show supporting text including Title, actors, director and showing times.

</details>

<details>
<summary>Screenshot</summary>
<img src = "https://i.imgur.com/LZ1SrWM.jpg">
</details>

<details>
<summary>Description</summary>

pressing on a movie tile or the appearing play button when hovering over a movie tile anywhere on the web page will redirect to the details page, including all required information, drawn from two api calls.
This includes
* a link to a youtube trailer
* directors, actors
* a highly interactive display for showing times (showing times are stored and retreived from the local database)
* production country
* genre(s)
* release date
* age requirement
* viewer rating (if prsent)
* movie description
* poster image
pressing on the age classification icon will take you to the classification page. Pressing "book now" for a time will take you to the booking site for that time/movie. 

</details>

<details>
<summary>Authors</summary>
    
*  **[Korbinian Ring](https://github.com/KMRRingQA)**
    
</details>

#### Opening Times
The opening times page:
* has a dedicated page to list the opening times. 
* is part of the overall site navigation
* has details about the opening times of the cinema.

#### Classifications
The classifications page:
* has a page dedicated to the film classification system.
* is part of the overall site navigation.
* addresses the standard film classifications, rules and conditions relating to each classification.
* link out to external resources with more detail on individual items.

#### Screens
The screens page:
* Includes an image of the seating plan of the standard & deluxe screen 
* Includes images showing the décor for the standard and deluxe seats.


#### Ticket Bookings
The ticket bookings page: 
* is part of the overall site navigation 
* includes a booking with the following information:
   * Movie title
   * Screening date and time
   * Name of booker
   * Number of seats
   * Adult
   * Child
   * Concession

#### Payment
The payment page:
* is dedicated to paying for tickets that are booked in advance.
* gathers the following information, from the user:
   * Card holder’s name
   * Card number
   * Expiry date
   * Security code/CVC
* with details saved and passed onto an external merchant for processing




## Building

How to build the project: 

### Built With

[Maven](https://maven.apache.org/) - Dependency Management

* Clone the repo to your machine.
* Open the cmd line / git bash inside the repo file directory.
* Run the following commands:

``` mvn clean package ```

``` mvn spring-boot:run ```

Note: When you run the second command the program will run, launching the Spring boot application. You can then navigate to `localhost:8181` via a browser or my shortcut provided in this repo, to reach the home page of the web interface. The app will run until you trigger the `/shutdownAppContext` API call.


## Usage

This project is a demonstration of Spring libraries, Databases, API calls, Front-End Delvelopment & Testing.


**[Back to top](#table-of-contents)**

## Further Reading

Spring:
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-documentation)


Front-end:
- [Get started with Bootstrap](https://getbootstrap.com/docs/4.5/getting-started/introduction/)


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

- **[Owen Jackson](https://github.com/OJackson1)
- **[Tay Dzonu](https://github.com/Tay-Dzonu-QA)
- **[Mohammad Rashid](https://github.com/MRashid98)
- **[Oforitsesan Towesho](https://github.com/CodewithOsatQA)

## Acknowledgements

- Probably not needed currently, but teams may wish to credit those outside the academy/stackoverflow users/reddit posts/whatever else they've used here
