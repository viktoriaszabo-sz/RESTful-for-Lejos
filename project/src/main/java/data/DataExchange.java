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
import data.LineFollower;
import data.UltrasonicSensor;

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
    
    @Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;
	
    
    
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
	
	
	 public int getSpeed() {
	        return speed;
	    }

	    public void setSpeed(int speed) {
	        this.speed = LineFollower.SPEED;
	    }

	    public int getTurnangle() {
	        return turnangle;
	    }

	    public void setTurnangle(int turnangle) {
	        this.turnangle = LineFollower.TURN_ANGLE;
	    }

	    public int getMaxobs() {
	        return maxobs;
	    }

	    public void setMaxobs(int maxobs) {
	        this.maxobs = LineFollower.MAX_OBSTACLES;
	    }

	    public float getSecuritydis() {
	        return securitydis;
	    }

	    public void setSecuritydis(float securitydis) {
	        this.securitydis = UltrasonicSensor.securityDistance;
	    }
    
}