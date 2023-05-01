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
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;

import conn.Connections;

import data.Attri;
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
	public Attri addEvByPost (@DefaultValue("300") @FormParam("speed") int speed, 
									 @DefaultValue("220") @FormParam("turnangle") int turnangle, 
									 @DefaultValue("2") @FormParam("maxobs") int maxobs, 
									 @DefaultValue("9") @FormParam("securitydis") float securitydis)
	{
		Attri a = new Attri(speed, turnangle, maxobs, securitydis);
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			a = new Attri(0, 0, 0, 0);
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
	
	@GET
	@Path("/read_ev")
	public void readEV3() 
    {
		ArrayList<DataExchange> list=new ArrayList<>();
		Connection conn=null;
		try{
			conn=Connections.getConnection();
		
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from walle");
			while (RS.next()) {
				DataExchange d = new DataExchange();
				d.setSpeed(RS.getInt("speed"));
				d.setTurnangle(RS.getInt("turnangle"));
				d.setMaxobs(RS.getInt("maxobs"));
				d.setSecuritydis(RS.getFloat("securitydis"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/readev3.jsp");
		//request dispatcher object 
		//we need to create a new .jsp file within the webapp
		
		request.setAttribute("dataexchange", list);
		//"dogs" will be the "${requestscope.dogs}" in the jsp file 
		
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}