import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class LineFollower extends Thread {
	
	public final float BLACK_THRESHOLD = 0.1f;

    DataExchange DE;
    
    public LineFollower(DataExchange DE) { 
    	this.DE = DE; 
    } 

    public void run()
    {
    	int SPEED = 300;
    	int TURN_ANGLE = 220;				//we initialize the original values first, then use the altered values from the database
    	final int MAX_OBSTACLES = 2;
    	
    	System.out.println("INITIALIZING OPERATION WALL-E");
    	
    	int obstacleCount = 0;
    	
        while(!Button.ESCAPE.isDown() || obstacleCount < DE.getMaxobs() ) //new values read from the web service database
        {
        	float[] colorSample = DataExchange.colorSample;
            
            if (DataExchange.getCMD() == 1) //LineFollower code if no obstacle is detected
            {
            	if(colorSample [0] == Color.BLACK) {
                	// On the line, move straight
                	Motor.A.setSpeed(DE.getSpeed());
                	Motor.B.setSpeed(DE.getSpeed());
                	Motor.A.forward();
                    Motor.B.forward();
            	} 
           		else {
                	// Off the line, adjust direction
                	if(colorSample[0] < BLACK_THRESHOLD) {
                    	// Too far to the right, turn left
                    	Motor.A.setSpeed(DE.getSpeed() / 4);
                    	Motor.B.setSpeed(DE.getSpeed());
                    	Motor.A.forward();
                        Motor.B.forward();
                	} 
                	else if (colorSample[0] > BLACK_THRESHOLD){

                    	// Too far to the left, turn right
                    	Motor.A.setSpeed(DE.getSpeed());
                    	Motor.B.setSpeed(DE.getSpeed() / 2);
                    	Motor.A.forward();
                        Motor.B.forward();
                	}
            	}
            }
            else if (DataExchange.getCMD() == 2){ //if obstacle is detected
            	
            	System.out.println("Obstacle detected!");
            	obstacleCount++;
            	if (obstacleCount == DE.getMaxobs()) { // stop after the second encounter
                    Motor.A.stop();
                    Motor.B.stop();
                    interrupt();
                    if (LineFollower.interrupted()) {
                    	 
                    	System.out.println("DISRESPECT YOUR		SURROUNDINGS!");
                    	
                    	Motor.A.setSpeed(90); 
                    	Motor.B.setSpeed(90);
                    	
                    	Motor.A.forward();
                    	Motor.B.backward();
                    	
                		Sound.playSample(new File ("dis.wav"), Sound.VOL_MAX);
                		Delay.msDelay(1000);
                		
                		Motor.A.stop();
    					Motor.B.stop();
    					interrupt();
    					//return;
                    }
                }
            	Motor.A.setSpeed(DE.getSpeed());
            	Motor.B.setSpeed(DE.getSpeed()); 
            	Motor.A.forward();
                Motor.B.forward();
                Delay.msDelay(75);
                Motor.A.stop();
                Motor.B.stop();
                //actual avoidance happening
                Motor.A.rotate(-DE.getTurnangle()); //turns out sharply so that it can avoid the obstacle
                Delay.msDelay(10); 			
                Motor.A.setSpeed(DE.getSpeed()/4);  
                Motor.B.setSpeed(DE.getSpeed());
            }
        } 
    }
}
