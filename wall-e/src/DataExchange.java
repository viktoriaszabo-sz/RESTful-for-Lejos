import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//THIS IS FOR REGULAR DATAEXCHANGE + SETTING NEW VALUES FROM DATABASE TO THE ROBOT
//this reads stuff from the servlet "read_ev" method

public class DataExchange 
{
	//colorSensor
    public static EV3ColorSensor colorSensor;
    public static final float[] colorSample = new float[1]; 
    //ultrasonic sensor 
    public EV3UltrasonicSensor sonicSensor; 
    
    private int speed; 
    private int turnangle; 
    private int maxobs; 		//these are the attributes from the Attri class - we initialize them here as well 
    private float securitydis;	//then later in the setters we set their values to our original robot values
    
    /*@Context
	HttpServletRequest request; 
	@Context 
	HttpServletResponse response;*/
	
    public DataExchange(int speed, int turnangle, int maxobs, float securitydis)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;					//parameterized constructor for ev3_service
    	this.maxobs = maxobs;						//we will use this in the read_ev method
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
	
	
	 public int getSpeed() {  				//these are the data we got from the database in
	        return speed;					// the Walle class
	    }				

	    public void setSpeed(int speed) {
	        this.speed = LineFollower.SPEED;	//we set the Attri values to our original robot values
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