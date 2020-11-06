@tagAlbum
Feature: AlbumPage
  I want to use this template for my feature file

  Scenario Outline: ALBUM open albums page - LOGGED IN
    Given I am logged in and on the Album page
    When I click on the Album with id <id>
    Then I will be on the Album page with album id <id> and be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: ALBUM open albums page - NOT LOGGED IN
    Given I am not logged in and on the Album page
    When I click on the Album with id <id>
    Then I will be on the Album page with album id <id> and not be logged in

    Examples: 
      | id |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: ALBUM To Artists Page - NOT LOGGED IN
    Given I am not logged in and on the Album page
    When I open the album side bar and select Albums by <artistID>
    Then I will be on the Artists page for <artistID> and not be logged in

    Examples: 
      | artistID |
      |        1 |
      |        2 |

  Scenario Outline: ALBUM To Artists Page - LOGGED IN
    Given I am logged in and on the Album page
    When I open the album side bar and select Albums by <artistID>
    Then I will be on the Artists page for <artistID> and be logged in

    Examples: 
      | artistID |
      |        1 |
      |        2 |

  Scenario Outline: ALBUM To Genre Page - NOT LOGGED IN
    Given I am not logged in and on the Album page
    When I open the album side bar and select <GenreID> Albums
    Then I will be on the Genre page for <GenreID> and not be logged in

    Examples: 
      | GenreID |
      |       1 |
      |       2 |

  Scenario Outline: ALBUM To Genre Page - LOGGED IN
    Given I am logged in and on the Album page
    When I open the album side bar and select <GenreID> Albums
    Then I will be on the Genre page for <GenreID> and be logged in

    Examples: 
      | GenreID |
      |       1 |
      |       2 |
