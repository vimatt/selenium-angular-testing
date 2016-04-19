Feature: Testing and demoing the framework functionality on the angular demo site

  Scenario: Demonstrating the waitForAngular function
    Given that we navigate to localhost
    And that we know the element with the "id" named "favorites-button" as "button"
    And click the element "button"
    Then the first cell in the table should be "Fargo"

  Scenario: Functionality of ByAngular.model function
    Given that we navigate to localhost
    And that we know the element with the "model" named "search" as "input1"
    And input "Fargo" to the element "input1"
    And that we know the element with the "id" named "search-field" as "input2"
    Then the attribute "value" of "input2" should be "Fargo"

  Scenario: Functionality of ByAngular.binding function
    Given that we navigate to localhost
    And that we know the element with the "binding" named "search" as "search"
    Then the text of the element "search" should be "You are searching for a MOVIE:"
    And that we know the element with the "id" named "search-field" as "input"
    And input "Men in Black" to the element "input"
    Then the text of the element "search" should be "You are searching for a MOVIE: Men in Black"

  Scenario: Functionality of ByAngular.options function
    Given that we navigate to localhost
    And that we know the element with the "options" named "types.name for types in types.availableTypes track by types.id" as "radio-options"
    Then the text of the 1st element of "radio-options" should be "movies"
    Then the text of the 2nd element of "radio-options" should be "series"

  Scenario: Functionality of ByAngular.repeater function
    Given that we navigate to "http://localhost:9090/#/favorites/movies"
    And that we know the element with the "repeater" named "movie in movies" as "movies"
    Then we should have 5 elements in "movies"

  Scenario: Functionality of ByAngular.repeater.row function
    Given that we navigate to "http://localhost:9090/#/favorites/movies"
    And that we know the repeater element "movie in movies" as "movies" with row 1
    Then the text of the element "movies" should be "Fargo 1996 Crime, Drama, Thriller 98 min 8.2"

  Scenario: Functionality of ByAngular.repeater.row.cell function
    Given that we navigate to "http://localhost:9090/#/favorites/movies"
    And that we know the repeater element "movie in movies" as "movies" with row 1 and column 2
    Then the text of the element "movies" should be "1996"