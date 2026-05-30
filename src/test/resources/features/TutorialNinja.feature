Feature: Tutorial Ninja Simple Login and Shopping
  Background: 
    Given User is on the Tutorial Ninja application
    
  @Smoke @Regression
  Scenario Outline: Successful login, search, shopping cart, and logout
    When User clicks on My Account and selects Login
    And User enters email "<email>" and password "<password>"
    And User clicks the Login button
    Then User is logged in successfully
    When User searches for "<product>" and adds it to the cart
    And User opens the cart and clicks View Cart
    And User removes the item from the cart
    Then User securely logs out

    Examples:
      | email                 | password | product |
      | ayush264180@gmail.com | demo2618 | iphone  |
      | ayushlcfr@gmail.com   | demo2618 | MacBook |