  #This is a comment in a Feature File
  Feature: Login link work
    Background:   # Will execute these before EACH Scenario
      Given The User is on the Login Page
    #Each Scenario will correspond to a User Story
    Scenario Outline: Username and Password is valid
      When The User enter "<username>" in the username input and "<password>" in the password input
      Then The User should be on the Main Page

      Examples:
        | username | password |
        | CS     | CS123 |
        | Chris     | Chris123 |

    Scenario Outline: Username and Password is invalid
      When The User enter "<username>" in the username input and "<password>" in the password input
      Then The User should see an alert pop up and the username input and password input should be empty

      Examples:
        | username | password |
        | qweqw    | qweasx |
        | xzz      | zxczx |