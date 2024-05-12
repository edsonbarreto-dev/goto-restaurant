Feature: Customer Management

  Scenario: Finding a customer by document
    Given the customer document is not null
    When I search for a customer with document "9283736489"
    Then I should receive details of the customer