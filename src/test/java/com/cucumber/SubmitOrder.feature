@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order 
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name  								| password 		| productName   |status  |
      | tonystark15@gmail.com | tonyStark1$ | IPHONE 13 PRO	|success |
     
