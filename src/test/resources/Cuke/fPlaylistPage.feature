@tagPL
Feature: PlaylistPage
  I want to use this template for my feature file

  Scenario Outline: PLAYLIST open tracks page - LOGGED IN
    Given I am logged in and on Playlist 3 page
    When I click to view the Track with id <id> for Playlist 3
    Then I will be on the Track page with track id <id>

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
      | playlistID |
      |          1 |
      |          2 |
      |          3 |
      |          4 |

  Scenario Outline: PLAYLIST add Track - LOGGED IN
    Given I am logged in and on Playlist 4 page
    When I add Track <id> "<name>" to Playlist 4
    Then The Playlist 4 includes Track <id>

    Examples: 
      | id | name  |
      |  1 | track |
      |  2 | track |
      |  3 | track |
      |  4 | track |
      |  5 | track |

  Scenario Outline: PLAYLIST remove Track - LOGGED IN
    Given I am logged in and on Playlist <PlID> page
    When I remove Track <Trackid> from Playlist <PlID>
    Then The Track <Trackid> can not be found on Playlist <PlID>

    Examples: 
      | Trackid | PlID |
      |       1 |    1 |
      |       2 |    2 |

  Scenario Outline: PLAYLIST edit Playlist - LOGGED IN
    Given I am logged in and on Playlist <PlID> page
    When I edit the Playlist <PlID> info
    Then Playlist <PlID> info has been changed

    Examples: 
      | PlID |
      |    1 |
      |    2 |
      |    3 |
      |    4 |

  Scenario Outline: PLAYLIST delete Playlist - LOGGED IN
    Given I am logged in and on Playlist <PlID> page
    When I delete Playlist <PlID>
    Then Playlist <PlID> is not found

    Examples: 
      | PlID |
      |    1 |
      |    2 |
      |    3 |
      |    4 |
