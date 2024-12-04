Feature: User Creation and Login

  Scenario: Create a new user and login
    Given I authenticate with valid credentials
    When I create a new user with random data
#    Then I should save the credentials based on the role
#    And I should login using the saved credentials