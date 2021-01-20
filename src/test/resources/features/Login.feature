@login
Feature: As user I want to be able to login under different roles

  Background: common steps for each scenario in this particular feature file
    Given user is on the login page

  @non
  Scenario: Login as a sales manager
    When user logs in
    #read from configuration properties as sales manager since we did not provide any user
    Then user should see dashboard page

  @parametrized_test  @BUG_VY2323 @smoke_test
  Scenario: Parametrized login
    When user logs in as a "salesmanager"
    Then user should see dashboard page
  #"salesmanager" - is a parameter. "" allows to do test parametrization which helps to re-use test steps

  @negative_login @ie
  Scenario: Invalid password
    When user logs in with "storemanagers85" and "wrong" password
    Then user verifies that "Invalid user name or password." message is displayed


