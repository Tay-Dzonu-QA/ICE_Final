Feature: Navigating to Landing Page and Logging in
  I want to use this template for my feature file

 
     Scenario Outline: Landing Page
    Given I have my browser running
    When I enter the url
    Then I will be taken to the Landing Page

     Scenario Outline: Logging In
    Given I am on the landing page
    When I enter my username and password
    Then I will be taken to the Home Page
    
     Scenario Outline: Registering
    Given I am on the landing page
    When I enter my details
    Then I will create an account