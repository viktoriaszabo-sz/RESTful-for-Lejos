package project;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import data.DataExchange;

public class RobotClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uri = "http://127.0.0.1:8080/rest/project/add_ev3"; //maybe add the form parameters
		//String uri = "http://127.0.0.1:8080/rest/project/test"; //maybe add the form parameters
		Client c=ClientBuilder.newClient();
		//creates a new instance of a 'Client' class 
		//This class represents an HTTP client that can be used to send requests to a server.
		
		WebTarget wt=c.target(uri);
		//creates a new WebTarget instance
		//passing the uri, that we passed earlier
		
		Builder b=wt.request();
		//we build an instance on the WebTarget object 
		//This class is used to build and send requests to the server.
		
		DataExchange d=b.get(DataExchange.class); //retrieves the Dog object 
		String s=b.get(String.class); //retrieves the object as a JSON string 
		
		System.out.println("Object: "+d);
		System.out.println("JSON: "+s);
	}
}