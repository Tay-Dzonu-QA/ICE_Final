Feature: ArtistPage
  I want to use this template for my feature file

  Scenario Outline: ARTIST open artists page - LOGGED IN
    Given I am logged in and on the Artist page
    When I click on the Artist with id <id>
    Then I will be on the Artist page with artist id <id> and be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |

  Scenario Outline: ARTIST open artists page - NOT LOGGED IN
    Given I am not logged in and on the Artist page
    When I click on the Artist with id <id>
    Then I will be on the Artist page with artist id <id> and not be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |

  Scenario Outline: Change order - NOT LOGGED IN
    Given I am not logged in and on the Artist page
    When I open the side bar and select order by "<order>"
    Then I will be on the Artist page with order "<order>" and not be logged in

    Examples: 
      | order    |
      | default  |
      | desc     |
      | name     |
      | nameDesc |

  Scenario Outline: Change order - LOGGED IN
    Given I am logged in and on the Artist page
    When I open the side bar and select order by "<order>"
    Then I will be on the Artist page with order "<order>" and be logged in

    Examples: 
      | order    |
      | default  |
      | desc     |
      | name     |
      | nameDesc |
