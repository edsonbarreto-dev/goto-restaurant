Feature: Restaurant Management

  Scenario: Finding a restaurant by document
    Given the restaurant service is available
    When I search for a restaurant with document "987654321"
    Then I should receive details of the restaurant

  Scenario: Creating a new restaurant
    Given I have valid restaurant information
    When I create a new restaurant
    Then the restaurant should be successfully created

  Scenario: Updating customer information for a restaurant
    Given I have valid customer information
    And there is an existing restaurant with document "123456789"
    When I update the customer information for the restaurant
    Then the customer information should be successfully updated