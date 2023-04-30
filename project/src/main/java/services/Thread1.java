package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.appengine.repackaged.com.google.common.net.MediaType;

import conn.Connections;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.GET;
import javax.ws.rs.core.Context;

import conn.Connections;

import data.DataExchange;

public class Thread1 //this thread is for the robot to read attributes from the database 
					 // video 20 "Read a dog from the database and show it in a JSP file for update"
{
	@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response; 
	


    
    //these variables are only here for testing without the other hardware components: 
    private int speed = 0;
    private int turnangle = 0;
    private int maxobs = 0;
    private float securitydis = 0;
    
    
    
    
    public int getSpeed()
    {
    	return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTurnangle()
    {
    	return turnangle;
    }
    public void setTurnangle(int turnangle)
    {
    	this.turnangle = turnangle;
    }
    
    public int getMaxobstacle()
    {
    	return maxobs;
    }
    public void setMaxobstacle(int maxobs)
    {
    	this.maxobs = maxobs;
    }
    public float getSecuritydistance()
    {
    	return securitydis;
    }
    public void setSecuritydistance(float securitydis)
    {
    	this.securitydis = securitydis;
    }


    public DataExchange() {} //constructor
    public DataExchange(int speed, int turnangle, int maxobs, float securitydis)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;											//parameterized constructor for ev3_service
    	this.maxobs = maxobs;
    	this.securitydis = securitydis;
    }

    
    
    
    
    
    
/*    public static void setColorSample(float[] sample)
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
    */
}