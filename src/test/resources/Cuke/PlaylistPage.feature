Feature: PlaylistPage
  I want to use this template for my feature file

  Scenario Outline: PLAYLIST open tracks page - LOGGED IN
    Given I am logged in and on Playlist 3 page
    When I click to view the Track with id <id> for Playlist 3
    Then I will be on the Track page with track id <id> and be logged in

    Examples: 
      | id |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: PLAYLIST change playlist - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I open the Playlist side bar and select Playlist <playlistID>
    Then I will be on the Playlist page for <playlistID> and be logged in

    Examples: 
      | albumID |
      |       1 |
      |       2 |
      |       3 |
      |       4 |
      |       5 |

  Scenario Outline: PLAYLIST add Track - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I add Track <id> "<name>" to Playlist 4
    Then The Track <id> can be found on Playlist 4

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: PLAYLIST remove Track - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I remove Track <id> from Playlist 4
    Then The Track <id> can not be found on Playlist 4

  Scenario Outline: PLAYLIST edit Playlist - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I edit the Playlist 4 info
    Then Playlist 4 info has been changed
    
  Scenario Outline: PLAYLIST delete Playlist - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I delete Playlist 4
    Then Playlist 4 is not found
