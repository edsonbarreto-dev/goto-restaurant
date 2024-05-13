Feature: Customer Management

  Scenario: Finding a customer by document
    Given the customer document is not null
    When I search for a customer with document "80236626191"
    Then I should receive details of the customer