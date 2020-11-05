@tagGenre
Feature: GenrePage
  I want to use this template for my feature file

  Scenario Outline: GENRE open genre page - LOGGED IN
    Given I am logged in and on the Genre page
    When I click on the Genre with id <id>
    Then I will be on the Genre page with genre id <id> and be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |

  Scenario Outline: GENRE open genre page - NOT LOGGED IN
    Given I am not logged in and on the Genre page
    When I click on the Genre with id <id>
    Then I will be on the Genre page with genre id <id> and not be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |

  Scenario Outline: GENRE Change order - NOT LOGGED IN
    Given I am not logged in and on the Genre page
    When I open the genre side bar and select order by "<order>"
    Then I will be on the Genre page with order "<order>" and not be logged in

    Examples: 
      | order    |
      | default  |
      | desc     |
      | name     |
      | nameDesc |

  Scenario Outline: GENRE Change order - LOGGED IN
    Given I am logged in and on the Genre page
    When I open the genre side bar and select order by "<order>"
    Then I will be on the Genre page with order "<order>" and be logged in

    Examples: 
      | order    |
      | default  |
      | desc     |
      | name     |
      | nameDesc |
