package data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

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

//THIS IS FOR REGULAR DATAEXCHANGE + SETTING NEW VALUES FROM DATABASE TO THE ROBOT

@Path ("/project")
public class DataExchange 
{
	//colorSensor
    public static EV3ColorSensor colorSensor;
    public static final float[] colorSample = new float[1]; 

    //ultrasonic sensor 
    public EV3UltrasonicSensor sonicSensor; 
    
    private int speed; 
    private int turnangle; 
    private int maxobs; 
    private float securitydis;
    
    
    public int getSpeed()
    {
    	return Attri.getSpeed();
    }
    public void setSpeed(int speed) {
        LineFollower.SPEED = speed;
    }
    
    public int getTurnangle()
    {
    	return Attri.getTurnangle();
    }
    public void setTurnangle(int turnangle)
    {
    	LineFollower.TURN_ANGLE = turnangle;
    }
    
    public int getMaxobs()
    {
    	return Attri.getMaxobs();
    }
    public void setMaxobs(int maxobs)
    {
    	LineFollower.MAX_OBSTACLES = maxobs;
    }
    
    public float getSecuritydis()
    {
    	return Attri.getSecuritydis();
    }
    public void setSecuritydis(float securitydis)
    {
    	UltrasonicSensor.securityDistance = securitydis;
    }
    
    
    
    @Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;
	

	
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
    
    public DataExchange(int speed, int turnangle, int maxobs, float securitydis)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;											//parameterized constructor for ev3_service
    	this.maxobs = maxobs;
    	this.securitydis = securitydis;
    }
    
    public DataExchange() {} //constructor
    
    public static void setColorSample(float[] sample)
    {
    	colorSample[0] = sample[0];
    }
    
    public static int CMD = 1; //which command the robot should do -- 1=linefollowing 2=obstacle-avoidance
    
    public int command = 1;
    
    public static void setCMD(int command) {
		CMD = command;
	}
	
	public static int getCMD() {
		return CMD;
	}
    
}