
@new
Feature: Title of your feature

  @tag1
  Scenario: {12345}
    When user opens AmazonURL
    And user is on AmazonPage   
    When user stores the current url 
    #When user waits untill the visibility of AmazonText   
    
    #When user stores "innerText" of AmazonText into @Status  
    And user verify SearchBar1 is present with specialValue "Search Amazon.in"   
    When user waits untill the visibility of SearchBar
    Then user click on SearchBar
    When user enters "mobile" on control SearchBar
    And user waits for 2 seconds
    Then user press "ENTER"
    And user waits for 2 seconds
    And user verify Samsung is present with specialValue "mobile" 
    And user waits for 2 seconds
    When user waits untill the visibility of SearchBar
    When user enters "samsung" on control SearchBar
    And user waits for 2 seconds
    And user verify Samsung is not present with specialValue "samsungs" 
    #Then user press "ARROW_UP"
    Then user press "ENTER"
    And user waits for 2 seconds   
    Then user open the stored url    
    And user waits for 5 seconds    
    
