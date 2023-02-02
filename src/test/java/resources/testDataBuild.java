package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class testDataBuild {
	public addPlace addPlacePayLoad(String name,String language,String address)
	{
		addPlace p=new addPlace();
		p.setAccuracy(79);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 9853404878");
		p.setWebsite("https://rudracrashcourse.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("investing park");
		myList.add("office");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
        return p;
	}
	public String deletePayload(String placeid)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}";
	}

}
