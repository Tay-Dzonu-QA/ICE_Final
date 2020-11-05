@tagUser
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
      | id | name  |
      |  1 | track |
      |  2 | track |
      |  3 | track |
      |  4 | track |
      |  5 | track |

  Scenario Outline: USER remove Track - LOGGED IN
    Given I am logged in and on USER page
    When I remove Track <Trackid> from Playlist <PlID>
    Then The Track <Trackid> can not be found on Playlist <PlID>

    Examples: 
      | Trackid | PlID |
      |       1 |    1 |
      |       2 |    2 |

  Scenario Outline: USER edit Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I edit the Playlist <PlID> info
    Then Playlist <PlID> info has been changed USER

    Examples: 
      | PlID |
      |    1 |
      |    2 |
      |    3 |
      |    4 |

  Scenario Outline: USER delete Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I delete Playlist <PlID>
    Then Playlist <PlID> is not found

    Examples: 
      | PlID |
      |    1 |
      |    2 |
      |    3 |
      |    4 |

  Scenario Outline: USER Add Playlist - LOGGED IN
    Given I am logged in and on USER page
    When I add a new Playlist
    Then My new Playlist is found
