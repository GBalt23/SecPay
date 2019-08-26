#Author: georgebaltatzis23@gmail.com

Feature: SP Contact Us

  @simpleTestContactUs
  Scenario: Navigate to SP Contact Us and fill in details
    Given I open Google AU
    And I search for SecurePay
    And I bring up the SecurePay website
    When I select the ContactUs section
    Then I have the option to fill in several fields