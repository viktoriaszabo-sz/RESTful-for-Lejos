package services;

import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Context;

import data.LineFollower;
import data.UltrasonicSensor;
import data.DataExchange;

@Path ("/walle")
public class ev3_service {
	
	@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;
	private float securitydis; 
	
	//here we will type in all the different annotations for the robot 
	//i kept the inclass code to see how ours should look like 
	//all of them as @POST??

	@POST
	@Path("/ev3")
	@Produces(MediaType.APPLICATION_JSON)//Method returns object as a JSON string
	@Consumes("application/x-www-form-urlencoded") //Method can receive POSTed data from a html form
	public void addSettingsByPost (@DefaultValue("300")@FormParam("speed") int speed, @DefaultValue("220")@FormParam("turnangle") int turnangle, 
			@DefaultValue("2")@FormParam("maxobs") int maxobs, @DefaultValue("9") @FormParam("securitydis") float securitydis)
	{
		//we send the values as a form of a HTML form, and we keep the default values from the original code
		DataExchange d = new DataExchange(speed, turnangle, maxobs, securitydis);
		
		d.setSpeed(speed);
		d.setTurnangle(turnangle);
		d.setMaxobstacle(maxobs);
		d.setSecuritydistance(securitydis);
		
		ArrayList<DataExchange> list = getDataExchangeList(speed, turnangle, maxobs, securitydis);
		list.add(d);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/printev3.jsp");
		//request dispatcher object 
		//we need to create a new .jsp file within the webapp
		
		request.setAttribute("dataexchange", list);
		//"ev3" will be the "${requestscope.ev3}" in the jsp file 
		
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
		public ArrayList<DataExchange> getDataExchangeList(int speed, int turnangle, int maxobs, float securitydis){ //could be database handling even
			ArrayList<DataExchange> list = new ArrayList<>();
			list.add(new DataExchange(speed, turnangle, maxobs, securitydis));
			return list;
	}
	
	/*@GET
	@Path("/info")
	@Produces(MediaType.TEXT_HTML)
	public String info () {
		return "<h1> This is a Team 3's EV3 robot service project! </h1>";
	}
	
	@POST
	@Path("/set_speed/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public static void setSpeed(@PathParam("p1") int speed)
	{
		LineFollower.SPEED = speed;
	}
	
	@POST
	@Path("/set_angle/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public static void setAngle(@PathParam("p1") int angle)
	{
		LineFollower.TURN_ANGLE = angle;  
	}
	
	@POST
	@Path("/set_max_ob/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public static void setMaxOb(@PathParam("p1") int ob)
	{
		LineFollower.MAX_OBSTACLES = ob; 
	}
	
	@POST
	@Path("/set_dis/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public static void setDistance(@PathParam("p1") int distance)
	{
		UltrasonicSensor.securityDistance = distance; 
	}
	*/

}
