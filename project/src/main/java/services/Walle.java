package services;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import conn.Connections;

import data.Attri;

@Path ("/project")
public class Walle{
	
	public Walle() {}; //this is our main service class, this is where we put data into the database		
						//and then read those from the database 
	@Context
	HttpServletRequest request; 
	@Context
	HttpServletResponse response;

	
	@POST					//this is for adding values to the database
	@Path("/add_ev")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //html form 
	@Produces(MediaType.APPLICATION_JSON)
	public Attri addEvByPost (@DefaultValue("300") @FormParam("speed") int speed, 
									 @DefaultValue("220") @FormParam("turnangle") int turnangle, 
									 @DefaultValue("2") @FormParam("maxobs") int maxobs)
	{
		Attri a = new Attri(speed, turnangle, maxobs);
		//we need to create a new object from the Attri class 
		//Attri class holds all the values that are needed for the services 
				
		Connection conn=null; //we initialize the connection value		
		
		try { 	//Using normal Prepared statement to add the values into the database
			conn=Connections.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("insert into walle(speed, turnangle, maxobs) values(?,?,?)");
			pstmt.setInt(1, speed);
			pstmt.setInt(2, turnangle);		//we set the inserted values into place 
			pstmt.setInt(3, maxobs);		//check in db: "select * from walle;"
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			//this is for exception handling
			a = new Attri(0, 0, 0);
		}
		finally { //its needed whether or not the try-catch block produces something, 
					//so that the code still finishes
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return a;
	}
	
	/*@GET
	@Path("/read_ev")			//this is for reading the input values FROM the database
	public void ReadEvByPost() 			//this will be the main method of this thread
    {							//the robot will read this attributes and act accordingly
		ArrayList<Attri> list=new ArrayList<>();
		Connection conn=null;
		try{
			conn=Connections.getConnection(); 	//connection to db / mysql
		
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from walle");
			while (RS.next()) {
				Attri d = new Attri(); 				//creating a new object from DataExchange, since
				d.setSpeed(RS.getInt("speed"));		 // the robot will only work with the stuff put in that class
				d.setTurnangle(RS.getInt("turnangle"));	//just like with the previous project 
				d.setMaxobs(RS.getInt("maxobs"));		//these setters are in the DataExchange class as well
				list.add(d);
			}
		} catch (SQLException e) {		//exception-handling
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/readev3.jsp");
		//we need to create a new .jsp file within the webapp - or just to print it out on the screen
		
		request.setAttribute("attri", list);
		//"attri" will be the "${requestscope.dataexchange}" in the jsp file 
		
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) { //exception-handling
			e.printStackTrace();
		} 
	}*/
	/*@GET
	@Path ("/read_only_one_ev/{id}") 
	public void ReadOnlyOneEv(@PathParam("id") int id)
	{
		ArrayList<Attri> list=new ArrayList<>();
		Connection conn=null;
		//Attri d = new Attri();
		try{
			conn=Connections.getConnection(); 	//connection to db / mysql
		
			PreparedStatement pstmt=conn.prepareStatement("select * from walle where id=?");
			pstmt.setInt(1,  id);
			ResultSet RS=pstmt.executeQuery();
			if (RS.next()) {
				Attri d = new Attri(); 				//creating a new object from DataExchange, since
				d.setSpeed(RS.getInt("speed"));		 // the robot will only work with the stuff put in that class
				d.setTurnangle(RS.getInt("turnangle"));	//just like with the previous project 
				d.setMaxobs(RS.getInt("maxobs"));		//these setters are in the DataExchange class as well
				list.add(d);
			}
		} catch (SQLException e) {		//exception-handling
			e.printStackTrace();
		}
		finally {
			try {
				if (conn!=null) {
					conn.close();}
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/read_only_one_ev.jsp");
		//we need to create a new .jsp file within the webapp - or just to print it out on the screen
		
		request.setAttribute("attri", list);
		//"attri" will be the "${requestscope.dataexchange}" in the jsp file 
		
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) { //exception-handling
			e.printStackTrace();
		} 
	}*/
	@GET
	@Path ("/updatewalle/{id}")
	public String ReadByOneEv(@PathParam("id") int id, @FormParam("speed") int speed, @FormParam("turnangle") int turnangle, @FormParam("maxobs") int maxobs)
	{
		ArrayList<Attri> list=new ArrayList<>();
		Connection conn=null;
		//Attri d = new Attri();
		try{
			conn=Connections.getConnection(); 	//connection to db / mysql
		
			PreparedStatement pstmt=conn.prepareStatement("update walle set speed=?, turnangle=?, maxobs=? where id=?");
			pstmt.setInt(1,  id);
			ResultSet RS=pstmt.executeQuery();
			if (RS.next()) {
				Attri d = new Attri(); 				//creating a new object from DataExchange, since
				d.setSpeed(RS.getInt("speed"));		 // the robot will only work with the stuff put in that class
				d.setTurnangle(RS.getInt("turnangle"));	//just like with the previous project 
				d.setMaxobs(RS.getInt("maxobs"));		//these setters are in the DataExchange class as well
				list.add(d);
			}
		} catch (SQLException e) {		//exception-handling
			e.printStackTrace();
			return "Error occurred while fetching data from the database.";
		}
		finally {
			try {
				if (conn!=null) {
					conn.close();}
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/updatewalle.jsp");
		request.setAttribute("attri", list);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	
		if (list.isEmpty()) {
	        return "No data found for the given ID.";
	    }
		Attri at=list.get(0);
		return at.getSpeed()+" "+at.getTurnangle()+" "+at.getMaxobs();
		//maybe it should be set 
	}	
}