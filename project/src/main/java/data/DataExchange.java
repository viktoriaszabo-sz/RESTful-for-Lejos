package data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;


public class DataExchange 
{
    //public static final int SPEED = 300;
    //public static final float BLACK_THRESHOLD = 0.1f;

    //colorSensor
    public static EV3ColorSensor colorSensor;
    public static final float[] colorSample = new float[1];

    
    //obstacledetector
    //public static int TURN_ANGLE = 180; // angle to turn when avoiding obstacle
    //public static double distance = 0.11;
    
    //ultrasonic sensor 
    public EV3UltrasonicSensor sonicSensor;
    
    public int getSpeed()
    {
    	return LineFollower.SPEED;
    }
    
    public int getTurnangle()
    {
    	return LineFollower.TURN_ANGLE;
    }
    public int getMaxobstacle()
    {
    	return LineFollower.MAX_OBSTACLES;
    }
    public float getSecuritydistance()
    {
    	return UltrasonicSensor.securityDistance;
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