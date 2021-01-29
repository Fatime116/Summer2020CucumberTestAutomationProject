Feature: As user, I want to be able to to create new cars

  @add_car
  Scenario: 1.Add some cars
    Given user is on the login page
    And user logs in as a "storemanager"
    And user navigates to "Fleet" and "Vehicles"
    And user clicks on create car button
    When user adds new vehicle information
    |License Plate|SDET|
    |Model Year   |2021|
    And user clicks on save and close button
    #its implementation is  on common step_definition class




  @add_car_scenario_outline
  Scenario Outline: 1.Add some cars for <license plate> plates and <model year> year
    Given user is on the login page
    And user logs in as a "<role>"
    And user navigates to "Fleet" and "Vehicles"
    And user clicks on create car button
    When user adds new vehicle information
      |License Plate|<license plate>|
      |Model Year   |<model year>|

    And user clicks on save and close button

    Examples: test data
      | license plate | model year | role         |
      | Florida       | 2020       | storemanager |
      | QA            | 2021       | storemanager |
      | SDET          | 2020       | salesmanager |