import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

//testing CodeOwner feature by yours truly KatieRJ :)

public class DataExchange 
{
    //public static final int SPEED = 300;
    //public static final float BLACK_THRESHOLD = 0.1f;
	
public DataExchange() {} //constructor
    
    public DataExchange(int speed, int turnangle, int maxobs) 
    {
    	this.speed = speed; 
    	this.turnangle = turnangle;
    	this.maxobs = maxobs; 
	}
    private int speed;
    private int turnangle;
    private int maxobs;
     
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    public int getSpeed() {
    	return speed;
    }
    public void setTurnangle(int turnangle) {
    	this.turnangle = turnangle;
    }
    public int getTurnangle() {
    	return turnangle;
    }
    public void setMaxobs(int maxobs) {
    	this.maxobs = maxobs;
    }
    public int getMaxobs() {
    	return maxobs;
    }

    //colorSensor
    public static EV3ColorSensor colorSensor;
    public static final float[] colorSample = new float[1];
    //ultrasonic sensor 
    public EV3UltrasonicSensor sonicSensor;

	public static void setColorSample(float[] sample) {
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
