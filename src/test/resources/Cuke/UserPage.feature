Feature: UserPage
  I want to use this template for my feature file

  Scenario Outline: USER open tracks page - LOGGED IN
    Given I am logged in and on USER page
    When I click to view the Track with id <id> for Playlist 3
    Then I will be on the Track page with track id <id>

    Examples: 
      | id |
      |  3 |
      |  4 |
      |  5 |


  Scenario Outline: USER add Track - LOGGED IN
    Given I am logged in and on USER page
    When I add Track <id> "<name>" to Playlist 4
    Then The Playlist 4 includes Track <id>

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: USER remove Track - LOGGED IN
    Given I am logged in and on USER page
    When I remove Track <id> from Playlist 4
    Then The Track <id> can not be found on Playlist 4

  Scenario Outline: USER edit Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I edit the Playlist 4 info
    Then Playlist 4 info has been changed
    
  Scenario Outline: USER delete Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I delete Playlist 4
    Then Playlist 4 is not found

  Scenario Outline: USER Add Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I add a new Playlist
    Then My new Playlist is found