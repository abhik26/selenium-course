Feature: Application Login

  Scenario: Home page default login
  	Given User in on Netbanking landing page
  #	When User login into application with username and password
    When User login into application with username "jin" and password "1234"
    Then Home page is populated
    And Cards are displayed "true"
    
  Scenario: Home page default login
  	Given User in on Netbanking landing page
    When User login into application with username "john" and password "4321"
    Then Home page is populated
    And Cards are displayed "false"