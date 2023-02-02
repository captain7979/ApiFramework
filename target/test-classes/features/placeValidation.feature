Feature: validating Place API's
  I want to use this template for my feature file

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceApi
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with Status code 200
    And "status" in response body is "OK"
    And verify PlcedId created maps to "<name>" using "GetPlaceAPI"
    
    
    Examples:
    |name    |language|address              |
    |AAHouse |French  |wallstreet,italsy    |
   # |BBHouse |Spanich  |RoadStreet,italsy    |
  
@DeletePlace
Scenario: verify if delete place functionality is working
Given DeletePLace payload
When user calls "DeleytePlaceAPI" with "POST" http request
Then the API call got success with Status code 200
And "status" in response body is "OK"
