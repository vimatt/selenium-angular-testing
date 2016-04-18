Feature: Auto tests of the Demo Site

  Scenario: Verify the page title
    Given that we navigate to localhost
    Then the title of the page should be "Demo Site"

  Scenario: Do a movie search and continue to it's details page
    Given that we navigate to localhost
    And we wait for angular
    And that we know the element with the "id" named "search-field" as "input"
    And input "Fargo" to the element "input"
    And that we know the element with the "xpath" named "html/body/div/div/div[3]/div[1]/div/div/p[2]/a" as "button"
    And click the element "button"
    And we wait for angular
    And that we know the element with the "css" named "[class='media-title'] > a" as "title"
    Then the text of the element "title" should be "Fargo"
    And that we know the element with the "id" named "back-btn" as "back"
    And click the element "back"
    Then the URL should be "http://localhost:9090/#/search"

  Scenario: Do a series search and continue to it's details page, and then to the season page
    Given that we navigate to localhost
    And we wait for angular
    And that we know the angular element with the "model" named "type" as "radio"
    And click the 2nd angular element "radio"
    And that we know the element with the "id" named "search-field" as "input"
    And input "True Detective" to the element "input"
    And that we know the element with the "xpath" named "html/body/div/div/div[3]/div/div/div/p[2]/a" as "button"
    And click the element "button"
    And we wait for angular
    And that we know the element with the "css" named "[class='media-title'] > a" as "title"
    Then the text of the element "title" should be "True Detective"
    And that we know the angular element with the "model" named "season" as "season-search"
    And input "1" to the angular element "season-search"
    And that we know the element with the "id" named "season-search" as "search-button"
    And click the element "search-button"
    And that we know the element with the "id" named "go-to-season" as "go"
    And we wait for angular
    And click the element "go"
    And that we know the angular element with the "repeater" named "episode in seasonData.Episodes" as "episodes"
    Then there should be 8 elements in repeater element "episodes"
    And that we know the element with the "id" named "back-btn" as "back"
    And click the element "back"
    Then the URL should be "http://localhost:9090/#/series/tt2356777"
    And click the element "back"
    Then the URL should be "http://localhost:9090/#/search"
    
  Scenario: Watch my favorite movies
    Given that we navigate to localhost
    And we wait for angular
    And that we know the angular element with the "options" named "types.name for types in types.availableTypes track by types.id" as "favorites"
    And choose the 1st angular option "favorites"
    And that we know the element with the "id" named "favorites-button" as "button"
    And click the element "button"
    And we wait for angular
    Then the URL should be "http://localhost:9090/#/favorites/movies"
    And that we know the angular element with the "repeater" named "movie in movies" as "movies"
    Then there should be 5 elements in repeater element "movies"

  Scenario: Watch my favorite series
    Given that we navigate to localhost
    And we wait for angular
    And that we know the angular element with the "options" named "types.name for types in types.availableTypes track by types.id" as "favorites"
    And choose the 2nd angular option "favorites"
    And that we know the element with the "id" named "favorites-button" as "button"
    And click the element "button"
    And we wait for angular
    Then the URL should be "http://localhost:9090/#/favorites/series"
    And that we know the angular element with the "repeater" named "movie in movies" as "series"
    Then there should be 5 elements in repeater element "series"