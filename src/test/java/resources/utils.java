package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream ps=new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalData("baseUrl"))
                .addQueryParam("key","qaclick123")                
                .addFilter(RequestLoggingFilter.logRequestTo(ps))
                .addFilter(ResponseLoggingFilter.logResponseTo(ps))
                .setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalData(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("D:\\eclipseworkspace\\API_FeameWork\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonpath(Response resp,String key)
	{
		String res1=resp.asString();
		JsonPath js=new JsonPath(res1);
		return js.get(key).toString();
	}
	

}
