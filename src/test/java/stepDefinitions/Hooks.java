package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void beforeScenario() throws IOException
	{
		
		stepDefinition s=new stepDefinition();
		if(stepDefinition.placeid==null)
		{
		s.add_place_payload_with("Rudra", "Odia", "Bodhanga");
		s.user_calls_with_http_request("AddPlaceAPI", "POST");
		s.verify_plced_id_created_maps_to_using("Rudra", "GetPlaceAPI");
		}
	}
}
