package data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

//THIS IS FOR SHARING ATTRIBUTES FROM EV3 TO THE SERVICE

public class Attri 
{
    //these variables are needed for the setters + getters --> the service works with setters and getters, so 
	//we need to initialize our attributes as private - also name them different to be able to differentate them 
	//from the attributes that we will connect them with in the DataExchange class (ex.: MAX_TURNANGLE, MAX_OBSTACLE) 
    private int speed = 0;
    private int turnangle = 0;
    private int maxobs = 0;
     
    //regular setters and getters:
    
    public  int getSpeed()
    {
    	return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public  int getTurnangle()
    {
    	return turnangle;
    }
    public void setTurnangle(int turnangle)
    {
    	this.turnangle = turnangle;
    }
    
    public  int getMaxobs()				
    {
    	return maxobs;
    }
    public void setMaxobs(int maxobs)
    {
    	this.maxobs = maxobs;
    }
    
    
    public Attri() {} //constructor
    public Attri(int speed, int turnangle, int maxobs)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;				//parameterized constructor for ev3_service
    	this.maxobs = maxobs;
    }
}