


Feature: Company search

  As an e-commerce platform user.
  I want to be able to search for companies.
  So that I can find given company I'm interested in.


  Scenario: Search by name
    Given e-commerce platform home page is displayed
    When  search for company by name "Crif"
    Then  the user should be redirected to company search result page
    And   the search title should be "Crif"
    And   amount of returned companies should be more then 20

  Scenario: Search by name and province
    Given e-commerce platform home page is displayed
    When  search for company by name "Crif" with province "BO"
    Then  the user should be redirected to search result page
    And   the search title should be "Crif, BO"
    And   amount of returned companies should be more then 5

  Scenario: Search by name and incorrect province (-)
    Given e-commerce platform home page is displayed
    When  search for company by name "Crif" and province "BN"
    Then  the user should be redirected to search result page
    And   the search title should be "Crif, BN"
    And   warning message should be displayed "The search did not produce any results"





  Scenario: Search by tax code
    Given e-commerce platform home page is displayed
    When  search for company by tax code "02083271201"
    Then  the user should be redirected to search result page
    And   the search title should be "02083271201"
    And   amount of returned companies should be equal to 6

  Scenario: Search by tax code and province
    Given e-commerce platform home page is displayed
    When  search for company by tax code "02083271201" and province "BO"
    Then  the user should be redirected to company card page
    And   tax code should be equal to "02083271201"
    And   province should be equal to "BO"

  Scenario: Search by tax code less then 11 characters (-)
    Given e-commerce platform home page is displayed
    When  search for company by tax code "0208327120"
    Then  the user should be redirected to search result page
    And   the search title should be "0208327120"
    And   warning message should be displayed "Error identified in the tax code"

  Scenario: Search by tax code that doesn't exist (-)
    Given e-commerce platform home page is displayed
    When  search for company by tax code "0208327120X"
    Then  the user should be redirected to search result page
    And   the search title should be "0208327120X"
    And   warning message should be displayed "The search did not produce any results"

  Scenario: Search by tax code and incorrect province (-)
    Given e-commerce platform home page is displayed
    When  search for company by tax code "02083271201" and province "UD"
    Then  the user should be redirected to search result page
    And   the search title should be "02083271201, UD"
    And   warning message should be displayed "The search did not produce any results"





  Scenario: Search by VAT code
    Given e-commerce platform home page is displayed
    When  search for company by VAT code "02083271201"
    Then  the user should be redirected to search result page
    And   the search title should be "02083271201"
    And   amount of returned companies should be equal to 3

  Scenario: Search by VAT code and province
    Given e-commerce platform home page is displayed
    When  search for company by VAT code "02083271201" and province "BO"
    Then  the user should be redirected to company card page
    And   VAT code should be equal to "02083271201"
    And   province should be equal to "BO"

  Scenario: Search by VAT code less then 11 characters (-)
    Given e-commerce platform home page is displayed
    When  search for company by VAT code "0208"
    Then  the user should be redirected to search result page
    And   the search title should be "0208"
    And   warning message should be displayed "The search did not produce any results"




  Scenario: Search by REA code and province
    Given e-commerce platform home page is displayed
    When  search for company by REA code "410952" and province "BO"
    Then  the user should be redirected to company card page
    And   REA code should be equal to "410952"
    And   province should be equal to "BO"

  Scenario: Search by REA code (-)
    Given e-commerce platform home page is displayed
    When  search for company by REA code "410952"
    Then  the user should be redirected to search result page
    And   the search title should be "410952"
    And   warning message should be displayed "For research with REA Code you must specify the Province"

  Scenario: Search by REA code less then 6 characters and province (-)
    Given e-commerce platform home page is displayed
    When  search for company by REA code "41" and province "BO"
    Then  the user should be redirected to search result page
    And   the search title should be "41, BO"
    And   warning message should be displayed "The search did not produce any results"

  Scenario: Search for company with REA code and province (-)
    Given e-commerce platform home page is displayed
    When  search for company by REA code "410952" and province "BO"
    Then  the user should be redirected to company card page
    And   REA code should be equal to "410952"
    And   province should be equal to "BO"
