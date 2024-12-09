@smoke
Feature: User Creation and Login

  Scenario: Create a new user and login
    Given I authenticate with valid credentials
    When I create a new user with random data
     And I verify the created user exists
#    And I should login using the saved credentials