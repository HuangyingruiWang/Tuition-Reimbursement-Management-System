# Tuition-Reimbursement-Management-System

## Project Description

The purpose of TRMS is to provide a system that encourages quality knowledge growth relevant to an individual’s expertise.   Currently, TRMS provides reimbursements for university courses, seminars, certification preparation classes, certifications, and technical training.

## Technologies Used

* Java 8
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
** Install the correct version of Java 8 for your computer system

* To started the Application, you will need
** [IntelliJ](<https://www.jetbrains.com/idea/download/#section=windows>) + [Maven](https://maven.apache.org/download.cgi)
** Install the latest version of IntelliJ and Maven. 
** Extract Maven folder to your C drive.
** Open the Application folder on IntelliJ IDE, then right click on the project name -> Maven -> reload project. 

- All the `code` required to get started
- Images of what it should look like

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.

## Contributors

> Here list the people who have contributed to this project. (ignore this section, if its a solo project)

## License

This project uses the following license: [<license_name>](<link>).

