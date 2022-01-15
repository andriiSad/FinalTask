Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check search with correct data
    Given User opens '<homePage>' page
    And User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that product titles contains '<keyword>'
    Examples:
      | homePage                  | keyword |
      | https://www.asos.com/men/ | jordan  |

  Scenario Outline: Check search with incorrect data
    Given User opens '<homePage>' page
    And User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that 'NOTHING MATCHES YOUR SEARCH' message displayed
    Examples:
      | homePage                  | keyword            |
      | https://www.asos.com/men/ | dwiniwiqyqwuwqodqd |

  Scenario Outline: Check add product to wishList
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks 'Add to WishList' button on  first product
    And User clicks 'Saved items' icon
    Then User checks that wishlist header is '<header>'

    Examples:
      | homePage                  | keyword              | header |
      | https://www.asos.com/men/ | Napapijri Rainforest | 1 item |

  Scenario Outline: Check remove product from wishList
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks 'Add to WishList' button on  first product
    And User clicks 'Saved items' icon
    And User clicks 'Remove Item' button
    Then User checks that 'You have no Saved Items' message is displayed

    Examples:
      | homePage                  | keyword         |
      | https://www.asos.com/men/ | Nike Air Presto |

  Scenario Outline: Check add product to cart
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks 'Product Title Description' button on  first product
    And User clicks 'Size Select' dropDown Menu
    And User selects first clickable option, excludes 'Please select' option
    And User clicks 'Add to Bag' button
    Then User checks that item quantity in popUp shopping cart menu is '<itemQuantity>'

    Examples:
      | homePage                  | keyword          | itemQuantity |
      | https://www.asos.com/men/ | Adidas Originals | 1            |

  Scenario Outline: Check sale prices is lower than regular prices
    Given User opens '<homePage>' page
    And User moves cursor to 'Sales' navigation button
    And User clicks 'Best of sale' option from dropDown menu
    Then User checks that sale prices is lower than regular prices in <amountOfProductsToCheck> first items

    Examples:
      | homePage                  | amountOfProductsToCheck |
      | https://www.asos.com/men/ | 10                      |

  Scenario Outline: Check sort by price option
    Given User opens '<homePage>' page
    And User moves cursor to 'Sales' navigation button
    And User clicks 'Best of sale' option from dropDown menu
    And User clicks 'Sort' button
    And User clicks 'Price low to high' sort  option
    And User clicks 'Sort' button
    Then User checks that 'Price low to high' sort  option is highlighted
    And User checks that first <amountOfProducts> prices is displayed in descent order

    Examples:
      | homePage                  | amountOfProducts |
      | https://www.asos.com/men/ | 20               |

  Scenario Outline: Check logIn option without filling password input
    Given User opens '<homePage>' page
    And User moves cursor to 'My account' icon
    And User clicks 'Sign In' option in dropDown menu
    And User fills '<userName>' in 'User name' input
    And User clicks 'Sign In' button
    Then User checks that 'Hey, we need a password here' message displayed

    Examples:
      | homePage                  | userName                               |
      | https://www.asos.com/men/ | testLogInEmailAddressExample@gmail.com |

  Scenario Outline: Check logIn option with unregistered email address
    Given User opens '<homePage>' page
    And User moves cursor to 'My account' icon
    And User clicks 'Sign In' option in dropDown menu
    And User fills '<userName>' in 'User name' input
    And User fills '<password>' in 'Password' input
    And User clicks 'Sign In' button
    Then User checks that 'Incorrect email or password' message displayed

    Examples:
      | homePage                  | userName                                     | password |
      | https://www.asos.com/men/ | unRegisteredUserTestAddressExample@gmail.com | 11111    |

  Scenario Outline: Check currency change option
    Given User opens '<homePage>' page
    And User clicks 'Country Selector' button
    And User clicks 'Shop In' drop down menu
    And User clicks '<country>' option
    And User clicks 'Update Preferences' button
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that price is with '<currency>' currency

    Examples:
      | homePage                  | country | keyword | currency |
      | https://www.asos.com/men/ | Germany | jordan  | €        |