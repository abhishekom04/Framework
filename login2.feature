
@Google
Feature: Title of your feature

  @tag1
  Scenario: {12345}
    When user opens Google
    When user waits untill the visibility of SearchBox
    When user stores "innerText" of Home into "@Status.Value"
    #Then user gets innerText into @Status.Value
    And user compares "@Status.Value" to "Gmail"
    
    When user stores "innerText" of Image into @Status2.Value
    And user compares @Status2.Value to "Images"
    
    When user enters "@Status.Value" on control SearchBox
    Then user press "ENTER"
    And user waits for 5 seconds
    
    #And user verify Gmail is present with specialValue "{Gmail}" 
    #And user waits for 3 seconds
    #Then user checks for Home and opens "https://www.youtube.com/"
    #And user waits for 4 seconds
    
   
   