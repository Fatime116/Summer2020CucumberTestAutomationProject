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

  @parametrized_test
  Scenario: Parametrized login
    When user logs in as a "storemanager"
    Then user should see dashboard page

  @s_o
    #when we add<role>, after it run, it will show exact role in the report
  Scenario Outline: Parametrized login as <role>
    When user logs in as a "<role>"
    Then user should see dashboard page

    Examples:
      | role         |
      | salesmanager |
      | storemanager |
      #Auto-Formatting: command+option +L

  @s_o @with_two_columns
    #when we add<role>, after it run, it will show exact role in the report
  Scenario Outline: Parametrized login as <role>
    When user logs in as a "<role>"
    Then user should see "<page_title>" page
      #this new step, should implement it

    Examples:
      | role         | page_title      |
      | salesmanager | Dashboard       |
      | storemanager | Dashboard       |
      | driver       | Quick Launchpad |

  @negative_login @ie
  Scenario: Invalid password
    When user logs in with "storemanagers85" and "wrong" password
    Then user verifies that "Invalid user name or password." message is displayed


  @negative_scenario_outline
  Scenario Outline: Invalid login with <username> and <password>
    When user logs in with "<username>" and "<wrong password>" password
    Then user verifies that "<warning message>" message is displayed

    Examples:
      | username  | wrong password | warning message                |
      | fdsdf     | 7878787        | Invalid user name or password. |
      | hgf876876 | 676686         | Invalid user name or password. |