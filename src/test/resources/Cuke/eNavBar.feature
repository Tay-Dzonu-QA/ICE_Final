@tagNav
Feature: NavBar
  I want to use this template for my feature file

  Scenario Outline: Navigate from TRACK page - LOGGED IN
    Given I am logged in and on the "Track" page
    When I click on the "<page>" page
    Then I will be on the "<page>" page and be logged in

    Examples: 
      | page   |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Navigate from TRACK page - NOT LOGGED IN
    Given I am not logged in and on the "Track" page
    When I click on the "<page2>" page
    Then I will be on the "<page2>" page and not be logged in

    Examples: 
      | page2  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Navigate from ARTIST page - LOGGED IN
    Given I am logged in and on the "Artist" page
    When I click on the "<page>" page
    Then I will be on the "<page>" page and be logged in

    Examples: 
      | page   |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Navigate from ARTIST page - NOT LOGGED IN
    Given I am not logged in and on the "Artist" page
    When I click on the "<page2>" page
    Then I will be on the "<page2>" page and not be logged in

    Examples: 
      | page2  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Navigate from GENRE page - LOGGED IN
    Given I am logged in and on the "Genre" page
    When I click on the "<page>" page
    Then I will be on the "<page>" page and be logged in

    Examples: 
      | page   |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Navigate from GENRE page - NOT LOGGED IN
    Given I am not logged in and on the "Genre" page
    When I click on the "<page2>" page
    Then I will be on the "<page2>" page and not be logged in

    Examples: 
      | page2  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Navigate from ALBUM page - LOGGED IN
    Given I am logged in and on the "Album" page
    When I click on the "<page>" page
    Then I will be on the "<page>" page and be logged in

    Examples: 
      | page   |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Navigate from ALBUM page - NOT LOGGED IN
    Given I am not logged in and on the "Album" page
    When I click on the "<page2>" page
    Then I will be on the "<page2>" page and not be logged in

    Examples: 
      | page2  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Navigate from USER page - LOGGED IN
    Given I am logged in and on the "User" page
    When I click on the "<page>" page
    Then I will be on the "<page>" page and be logged in

    Examples: 
      | page   |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Navigate from INDEX page - NOT LOGGED IN
    Given I am not logged in and on the "index" page
    When I click on the "<page2>" page
    Then I will be on the "<page2>" page and not be logged in

    Examples: 
      | page2  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Log Out
    Given I am logged in and on the "<page4>" page
    When I click on the "Log Out" button
    Then I will be on the "index" page and not be logged in

    Examples: 
      | page4  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Log In
    Given I am not logged in and on the "<page5>" page
    When I click on the "Log In" button
    Then I will be on the "index" page and not be logged in

    Examples: 
      | page5  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |

  Scenario Outline: Choonz BTN - LOGGED IN
    Given I am logged in and on the "<page6>" page
    When I click on the choonz btn
    Then I will be on the "User" page and be logged in

    Examples: 
      | page6  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | User   |

  Scenario Outline: Choonz BTN - NOT LOGGED IN
    Given I am not logged in and on the "<page7>" page
    When I click on the choonz btn
    Then I will be on the "index" page and not be logged in

    Examples: 
      | page7  |
      | Track  |
      | Genre  |
      | Artist |
      | Album  |
      | index  |
