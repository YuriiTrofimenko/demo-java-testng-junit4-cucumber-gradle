Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check items searching
    Given User opens 'https://rozetka.com.ua/' home page
    When User filters products by category by keyword 'Ноутбуки'
    And  User filters products by brand by keyword '<keyword>'
    When User sorts products from expensive to cheap
    And  User selects the most expensive product
    Then User gets product title from product
    When User adds product to cart
    Then User gets product title from cart
    Then User gets cart total price
    Then User checks product title validation
    Then User checks that the total price of the shopping cart is less than 200000

    Examples:
      | keyword |
      | HP      |
      | Asus    |
      | Acer    |
      | Apple   |
      | Fujitsu |
