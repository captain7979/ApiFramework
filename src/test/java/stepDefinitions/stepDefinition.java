package stepDefinitions;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.testDataBuild;
import resources.utils;

public class stepDefinition extends utils{
	 RequestSpecification req1;
	 Response resp;
	 ResponseSpecification Respons;
		static String placeid;
		testDataBuild data=new testDataBuild();
	 @Given("Add Place Payload with {string} {string} {string}")
	 public void add_place_payload_with(String name, String language,String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 
	       
	         req1= given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	         Respons= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	 	    
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resources,String method) {
		APIResources resourceAPI=	APIResources.valueOf(resources);
		System.out.println(resourceAPI.getResource());
	  if(method.equalsIgnoreCase("POST"))
	  {
		resp= req1.when().post(resourceAPI.getResource());
		  
	  }
	  else if (method.equalsIgnoreCase("GET"))
		  resp= req1.when().get(resourceAPI.getResource());
	  
	
		   // resp= req1.when().post(resourceAPI.getResource());
		    		
	           // .then().assertThat().spec(Respons).extract().response();
	        //System.out.println(resp.asString());
	        
	  
	}

	@Then("the API call got success with Status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    
		assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.getStatusCode());
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
		
		assertEquals(getJsonpath(resp,keyvalue), expectedvalue);
	      
	}
	@Then("verify PlcedId created maps to {string} using {string}")
	public void verify_plced_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    
		 placeid=getJsonpath(resp,"place_id");
		 req1= given().spec(requestSpecification()).queryParam("place_id", placeid);
		 
		 user_calls_with_http_request(resource,"GET");
		String name= getJsonpath(resp, "name");
		System.out.println(name);
		assertEquals(name, expectedname);
	}
	@Given("DeletePLace payload")
	public void delete_p_lace_payload() throws IOException {
	   req1= given().spec(requestSpecification()).body(data.deletePayload(placeid));
	   
	}

}
