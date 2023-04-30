package services;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

import conn.Connections;

import data.DataExchange;

@Path ("/project")
public class ev3_service<Walle> {
	@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;

	
	@POST
	@Path("/add_ev")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //html form 
	@Produces(MediaType.APPLICATION_JSON)
	public DataExchange addEvByPost (@DefaultValue("300") @FormParam("speed") int speed, 
									 @DefaultValue("220") @FormParam("turnangle") int turnangle, 
									 @DefaultValue("2") @FormParam("maxobs") int maxobs, 
									 @DefaultValue("9") @FormParam("securitydis") float securitydis)
	{
		DataExchange d = new DataExchange(speed, turnangle, maxobs, securitydis);
		Connection conn=null; //we initialize the value
		/*try{
			conn=Connections.getConnection();
		}
		catch(Exception e) {
			d =new DataExchange(0,0, 0, 0); //if connection didnt work, it adds empty values to the database 
			System.out.println("DataExchange not added");//For debugging if connection fails
			return d;
		}*/
		//Using normal Prepared statement to add the values into the database
		try {
			conn=Connections.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("insert into walle(speed, turnangle, maxobs, securitydis) values(?,?,?,?)");
			pstmt.setInt(1, speed);
			pstmt.setInt(2, turnangle);
			pstmt.setInt(3, maxobs);
			pstmt.setFloat(4, securitydis);
			pstmt.executeUpdate();
			
			//Using common statement while reading, because there are no variables in the sql statement
/*			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from dog");
			while (RS.next()) {
				Dog dog=new Dog();
				dog.setId(RS.getInt("id"));
				dog.setBreed(RS.getString("breed"));
				dog.setWeight(RS.getFloat("weight"));
				list.add(dog);
			}*/ //this part reads stuff from the database
			
		} catch (SQLException e) {
			e.printStackTrace();
			d = new DataExchange(0, 0, 0, 0);
			System.out.println("error inserting data into database");
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
		return d;
	}
	
	/*public ArrayList<DataExchange> getDataExchangeList(int speed, int turnangle, int maxobs, float securitydis)
	{ //could be database handling even
		ArrayList<DataExchange> list = new ArrayList<>();
		list.add(new DataExchange(speed, turnangle, maxobs, securitydis));
		return list;
	}*/
	/*@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String info () {
		
		String info = "<h1> This is a project service! </h1>";
		request.setAttribute("dataexchange", info);
	    return "/jsp/printev3.jsp";
	}*/
}