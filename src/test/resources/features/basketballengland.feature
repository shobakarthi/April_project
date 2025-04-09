Feature: Test of basketball england website of User registration
  Scenario: Create an account with valid details
    Given I am on user registration page
    When I enter valid user details
    And I enter matching passwords
    And I accept the terms and conditions
    Then I submit the registration form
    And I should see a success message

   Scenario: Create an account with missing last name
     Given I am on user registration page
     When I enter details without a last name
      And I enter matching passwords
     And I submit the registration form
     Then I should see an error message for the missing last name


  Scenario: Create an account with mismatching passwords
    Given I am on user registration page
    When I enter details by giving different passwords
    And I submit the registration form
    Then I should see an error message for the mismatching passwords


  Scenario: Create an account by not accepting terms and conditions
    Given I am on user registration page
    When I enter valid user details
    And I enter matching passwords
    And I do not accept the terms and conditions
    Then I should see an error message that the terms and conditions are not approved
