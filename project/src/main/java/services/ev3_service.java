package services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	//all of them as @POST??

	@GET
	@Path("/info")
	@Produces(MediaType.TEXT_HTML)
	public String info () {
		return "<h1> This is a Team 3's EV3 robot service project! </h1>";
	}
	
	@POST
	@Path("/set_speed/{p1}")
	public static void setSpeed(@PathParam("p1") int speed)
	{
		LineFollower.SPEED = speed;
	}
	
	@POST
	@Path("/set_angle/{p1}")
	public static void setAngle(@PathParam("p1") int angle)
	{
		LineFollower.TURN_ANGLE = angle;  
	}
	
	@POST
	@Path("/set_max_ob/{p1}")
	public static void setMaxOb(@PathParam("p1") int ob)
	{
		LineFollower.MAX_OBSTACLES = ob; 
	}
	
	@POST
	@Path("/set_dis/{p1}")
	public static void setDistance(@PathParam("p1") int distance)
	{
		UltrasonicSensor.securityDistance = distance; 
	}

}
