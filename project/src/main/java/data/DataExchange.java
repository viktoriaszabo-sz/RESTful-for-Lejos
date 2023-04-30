package data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;


public class DataExchange 
{
    //public static final int SPEED = 300;
    //public static final float BLACK_THRESHOLD = 0.1f;

    //colorSensor
/*    public static EV3ColorSensor colorSensor;
    public static final float[] colorSample = new float[1]; */

    
    //obstacledetector
    //public static int TURN_ANGLE = 180; // angle to turn when avoiding obstacle
    //public static double distance = 0.11;
    
    //ultrasonic sensor 
/*    public EV3UltrasonicSensor sonicSensor; */
    
    
    /*private int speed = LineFollower.speed;
    private int turnangle = LineFollower.turn_angle;
    private int maxobs = LineFollower.max_obstacles;
    private float securitydis = UltrasonicSensor.securityDistance;*/
    
    
    
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