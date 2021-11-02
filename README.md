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
* The form that starts in less than two weeks will be marked as urgent(the options button will display in pink).
* If the current login user is some of those roles combination from above (A user is a direct supervisor, also a department head, and benefits coordinator), some of those approval processes can be skipped.
* Auto-Approval: A form is going to wait for its responder for a certain amount of time. If the time limit exceeds, and the form hasn't gotten any update, the auto-approval will auto-approve the form and update it to the next status.
* Each user is allowed to claim up to 1000$ in tuition reimbursement. 
* Once a user tries to apply a reimbursement amount larger than the amount available for him/her. This new form will go to the extra process. The benefits coordinator will decide the amount of exceeding available funds for this form or deny the form. And the user is able to confirm the change or cancel the form (delete the form).

To-do list:
* Wow improvement to be done 1
* Wow improvement to be done 2

## Getting Started
   
(include git clone command)
(include all environment setup steps)

> Be sure to include BOTH Windows and Unix command  
> Be sure to mention if the commands only work on a specific platform (eg. AWS, GCP)

- All the `code` required to get started
- Images of what it should look like

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.

## Contributors

> Here list the people who have contributed to this project. (ignore this section, if its a solo project)

## License

This project uses the following license: [<license_name>](<link>).

