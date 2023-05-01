package data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

//THIS IS FOR SHARING ATTRIBUTES FROM EV3 TO THE SERVICE
public class Attri 
{
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
    
    public int getMaxobs()
    {
    	return maxobs;
    }
    public void setMaxobs(int maxobs)
    {
    	this.maxobs = maxobs;
    }
    public float getSecuritydis()
    {
    	return securitydis;
    }
    public void setSecuritydis(float securitydis)
    {
    	this.securitydis = securitydis;
    }


    public Attri() {} //constructor
    public Attri(int speed, int turnangle, int maxobs, float securitydis)
    {
    	this.speed = speed;
    	this.turnangle = turnangle;											//parameterized constructor for ev3_service
    	this.maxobs = maxobs;
    	this.securitydis = securitydis;
    }

}