package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

import conn.Connections;

import data.Attri;

@Path ("/project")
public class Walle{
	
	public Walle() {}; //this is our main service class		
						
	@Context
	HttpServletRequest request; 
	@Context
	HttpServletResponse response;

	
	@POST					//this method is for inserting data into the database based on the ev3.html form 
	@Path("/add_ev")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Attri addEvByPost (@DefaultValue("300") @FormParam("speed") int speed, 
									 @DefaultValue("220") @FormParam("turnangle") int turnangle, 
									 @DefaultValue("2") @FormParam("maxobs") int maxobs)
	{
		Attri a = new Attri(speed, turnangle, maxobs);
		//Attri class holds all the values that are needed for the services 
				
		Connection conn=null; 		
		
		try { 	
			conn=Connections.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("insert into walle(speed, turnangle, maxobs) values(?,?,?)");
			pstmt.setInt(1, speed);
			pstmt.setInt(2, turnangle);		
			pstmt.setInt(3, maxobs);		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
			a = new Attri(0, 0, 0);
		}
		finally { 
					
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
	
	
	@GET
	@Path ("/read_by_lego") 					//this method will be used for the actual robot to read the required data from the database
	public String ReadByOneEv()
	{
		ArrayList<Attri> list=new ArrayList<>();
		Connection conn=null;
		//Attri d = new Attri();
		try{
			conn=Connections.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select * from walle order by id desc"); //it reads the latest input from the database table
			ResultSet RS=pstmt.executeQuery();
			if (RS.next()) {
				Attri d = new Attri(); 				
				d.setSpeed(RS.getInt("speed"));		 // the robot will only work with the attributes put in that class
				d.setTurnangle(RS.getInt("turnangle"));	
				d.setMaxobs(RS.getInt("maxobs"));		//these setters are in the DataExchange class as well
				list.add(d);
			}
		} catch (SQLException e) {		
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
		if (list.isEmpty()) {
	        return "No data found for the given ID.";
	    }
		Attri at=list.get(0);
		return at.getSpeed()+" "+at.getTurnangle()+" "+at.getMaxobs();
	}
	
	
	@POST					//this method is for updating the current values in the database with the values inserted from the updatewalle.jsp form
	@Path("/updatewalle")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	public void UpdateWalle(
			 @DefaultValue("300") @FormParam("speed") int speed, 
			 @DefaultValue("220") @FormParam("turnangle") int turnangle, 
			 @DefaultValue("2") @FormParam("maxobs") int maxobs)
	{
		Connection conn=null; 	
		
		try { 	
			conn=Connections.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("update walle set speed=?, turnangle=?, maxobs=? ORDER BY id DESC LIMIT 1");
			pstmt.setInt(1, speed);																		//updates the latest element
			pstmt.setInt(2, turnangle);		
			pstmt.setInt(3, maxobs);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		finally { 
			
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}