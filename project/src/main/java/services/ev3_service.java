package services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Context;

import data.Main;
import data.LineFollower;
import data.ColorSensor;
import data.UltrasonicSensor;
import data.DataExchange;

@Path ("/walle")
public class ev3_service {
	
	@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response; 
	
	
	//here we will type in all the different annotations for the robot 
	//i kept the inclass code to see how ours should look like 
	
	/*
	 * 
	@GET
	@Path("/getdog")
	@Produces(MediaType.APPLICATION_JSON)
	public Dog getDog() {
		Dog d = new Dog (1, "Laika", 28); 
		return d; 
	}
	
	
	@GET
	@Path("/getdogsbyweight/{p1}/{p2}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Dog> getDogsByName(@PathParam("p1") float low, @PathParam("p2") float high){
		ArrayList<Dog> list = getDogList(); 
		ArrayList<Dog> result = new ArrayList<>(); 
		for (Dog d:list) {
			if (d.getWeight()>= low && d.getWeight()<= high) //loops through the doglist between the weight 
															//low and high set in the beginning 
			{
				result.add(d);
			}
		}
		return result; 
	}
	
	public ArrayList<Dog> getDogList(){ //could be database handling even
		ArrayList<Dog> list = new ArrayList<>();
		list.add(new Dog(1, "East Siberian Laika", 30));
		list.add(new Dog(2, "White Shepard", 25));
		list.add(new Dog(3, "Husky", 40));
		list.add(new Dog(4, "Chiwuawa", 1));
		return list;
	}
	*/
}
