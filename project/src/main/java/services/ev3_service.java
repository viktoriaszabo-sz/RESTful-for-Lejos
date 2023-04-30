package services;

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
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Context;

import data.DataExchange;

@Path ("/project")
public class ev3_service {
	@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;

	@POST
	@Path("/add_ev3")
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
	
		request.setAttribute("dataexchange", list);
		//"ev3" will be the "${requestscope.ev3}" in the jsp file 
		
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
		//return list;
	}
	public ArrayList<DataExchange> getDataExchangeList(int speed, int turnangle, int maxobs, float securitydis)
	{ //could be database handling even
		ArrayList<DataExchange> list = new ArrayList<>();
		list.add(new DataExchange(speed, turnangle, maxobs, securitydis));
		return list;
	}
	
	/*@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public void info () {
		
		String info = "<h1> This is a project service! </h1>";
		//return "<h1> This is a project service! </h1>";
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/printev3.jsp");
		request.setAttribute("dataexchange", info);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}*/
}
