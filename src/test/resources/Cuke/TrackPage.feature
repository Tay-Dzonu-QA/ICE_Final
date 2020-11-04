Feature: TrackPage
  I want to use this template for my feature file

  Scenario Outline: TRACK open tracks page - LOGGED IN
    Given I am logged in and on the Track page
    When I click on the Track with id <id>
    Then I will be on the Track page with track id <id> and be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: TRACK open tracks page - NOT LOGGED IN
    Given I am not logged in and on the Track page
    When I click on the Track with id <id>
    Then I will be on the Track page with track id <id> and not be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: TRACK To Albums Page - NOT LOGGED IN
    Given I am not logged in and on the Track page
    When I open the track side bar and select Tracks in album <albumID>
    Then I will be on the Albums page for <albumID> and not be logged in

    Examples: 
      | albumID |
      |       1 |
      |       2 |
      |       3 |
      |       4 |
      |       5 |

  Scenario Outline: TRACK To Albums Page - LOGGED IN
    Given I am logged in and on the Track page
    When I open the track side bar and select Tracks in album <albumID>
    Then I will be on the Albums page for <albumID> and be logged in

    Examples: 
      | albumID |
      |       1 |
      |       2 |
      |       3 |
      |       4 |
      |       5 |

  Scenario Outline: TRACK add to Playlist - LOGGED IN
    Given I am logged in and on the Track page
    When I add Track <id> to Playlist "4. test playlist"
    Then The Track <id> can be found on Playlist 4

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |
