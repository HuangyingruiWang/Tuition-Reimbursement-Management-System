# Tuition-Reimbursement-Management-System

## Project Description

The purpose of TRMS is to provide a system that encourages quality knowledge growth relevant to an individual’s expertise.   Currently, TRMS provides reimbursements for university courses, seminars, certification preparation classes, certifications, and technical training.

## Technologies Used

* Java 8
* HTML/CSS/JavaScript
* Hibernate
* Javalin
* PostgreSQL
* JUnit 5
* GSON
* Log4J
* Selenium
* Cucumber
* Postman

## Features

* All users can submit a new Reimbursements Form
* The current login user can see all his related forms that are in different status
* The current login user can approve forms if he/she is a direct supervisor, department head, or benefits coordinator.
* If the current login user is some of those roles combination from above (A user is a direct supervisor, also a department head, and benefits coordinator), some of those approval processes can be skipped.
* Leaders can request additional documents from all levels of employees. (For example, the benefits coordinator can request employee, direct supervisor, or department head one at a time for additional document). For the current version of the application, they can send the requests, but the file stored system does not exist.
* If the form is <2 weeks from beginning, it will be marked as urgent(the options button will display in pink).
* Auto-Approval: A form is going to wait for its responder for a certain amount of time. If the time limit exceeds, and the form hasn't gotten any update, the auto-approval will auto-approve the form and update it to the next status.
* Each user is allowed to claim up to 1000$ in tuition reimbursement. 
* Once a user tries to apply a reimbursement amount larger than the amount available for him/her. This new form will go to the extra process. The benefits coordinator will decide the amount of exceeding available funds for this form or deny the form. And the user is able to confirm the change or cancel the form (delete the form).

To-do list:
* Implement the files stored
   * Users can provide approval documents before they submit a new Form, and the corresponding approval process can be skipped.
   * An employee is able to upload real documents as a response to the leader's request.
* Add more CSS to my HTML web page, so users can have a better user experience.
* Edit some of my Java Codes to reduce the page load waiting time, so users can have a better browser experience.

## Getting Started
To clone this Repository to your local computer
```
git clone https://github.com/HuangyingruiWang/Tuition-Reimbursement-Management-System.git
```
## Pre-Installed 
* [JDK](<https://www.oracle.com/java/technologies/downloads/#java8>)
  * Install the correct version of Java 8 for your computer system

* [IntelliJ](<https://www.jetbrains.com/idea/download/#section=windows>) + [Maven](https://maven.apache.org/download.cgi)
  * Install the latest version of IntelliJ and Maven. 
  * Extract Maven folder to your C drive.
  * Open the Application folder on IntelliJ IDE, then right click on the project name -> Maven -> reload project. 

* [DBeaver](<https://dbeaver.io/>)
  * Install the latest version of DBeaver.
  * Connect to Your AWS PostgreSQL Database.
  * Run my database.sql script to generate all data entities and data.

## Usage
1. Set Environment Variables for DB_URL, DB_USERNAME, DB_PASSWORD
   * In Windows 10: click the start button on your keyboard or at the bottom left corner of your screen, type environment variables.
   * Click on the Edit the system environment variables. On the System Properties Window, click on the Environment Variables button. 
   * Under System variables, create new variables with the correct variable names from above, and variable values as your database-connection information.
   * You may also need to add those system environment variables into IntelliJ environment variables if you can not run the Java Application.
    * In your IntelliJ, under Run tag ->  Edit Configuration -> add those enviroment variables.
3. Run Java Application on IntelliJ.
4. Run Login.html page on Google Chrome.

## Data Entities

Name  | Description
------------- | -------------
Login  | Holds users login information, like username and password
Departments | Holds department information, like department name
Users  | Holds users personal information, also has a boolean value for isBenCo, and two references to departments and login
Department_Header | Holds information of which user is the department header of a certain department.
Supervisors | Holds information of who is the direct supervisor of some users.
Event_Type  | Holds event type information, like event name and reimbursements percentage.
Grade_Format  | Holds grade format information, like grade format name and cut-off grade.
Status  | Holds Status information, like the status name.
Events  | Holds events information, like start date, cost... and four references to grade format, event type, status, and user.
Files  |  Holds all uploaded files. Not implemented.

## End Point
* Login Validation:
GET : `/login`
* Request the user information for the current login account
GET : `/users/user_id`
* Request the direct supervisor information for the current login account
GET : `/supervisors/user_id`
* Request the department header information for the current login account
GET : `/heads/department_id`
* Request all events information for the current login account
GET : `/users/user_id/events`
* Request to create a new event for the current login account
POST : `/users/user_id/events`
* Request to update a event for the current login account
PUT : `/users/user_id/events/event_id`
* Request to delete event for the current login account
DELETE : `/users/user_id/events/event_id`

