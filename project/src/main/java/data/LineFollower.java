package data;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class LineFollower extends Thread {
    public static int SPEED = 300;
    public static final float BLACK_THRESHOLD = 0.1f;
    public static int TURN_ANGLE = 220;
    DataExchange DE; 
    public static int MAX_OBSTACLES = 0;
    

    public LineFollower(DataExchange DE) { 
    	this.DE = DE; 
    } 

    public void run()
    {
    	System.out.println("Operation WALL-E initiated");
        
    	final int MAX_OBSTACLES = 2;
    	int obstacleCount = 0;
    	
        while(!Button.ESCAPE.isDown() || obstacleCount < MAX_OBSTACLES) 
        {
        	float[] colorSample = DataExchange.colorSample;
            
            if (DataExchange.getCMD() == 1) //LineFollower code if no obstacle is detected
            {
            	if(colorSample [0] == Color.BLACK) {
                	// On the line, move straight
                	Motor.A.setSpeed(SPEED);
                	Motor.B.setSpeed(SPEED);
                	Motor.A.forward();
                    Motor.B.forward();
            	} 
           		else {
                	// Off the line, adjust direction
                	if(colorSample[0] < BLACK_THRESHOLD) {
                    	// Too far to the right, turn left
                    	Motor.A.setSpeed(SPEED / 4);
                    	Motor.B.setSpeed(SPEED);
                    	Motor.A.forward();
                        Motor.B.forward();
                	} 
                	else if (colorSample[0] > BLACK_THRESHOLD){

                    	// Too far to the left, turn right
                    	Motor.A.setSpeed(SPEED);
                    	Motor.B.setSpeed(SPEED / 2);
                    	Motor.A.forward();
                        Motor.B.forward();
                	}
            	}
            }
            else if (DataExchange.getCMD() == 2){ //if obstacle is detected
            	
            	System.out.println("Obstacle detected!");
            	obstacleCount++;
            	if (obstacleCount == MAX_OBSTACLES) { // stop after the second encounter
                    Motor.A.stop();
                    Motor.B.stop();
                    interrupt();
                    if (LineFollower.interrupted()) {
                        
                    	/*Celebration celeb = new Celebration();
                    	Destroy destroy = new Destroy ();
                    	
                    	celeb.start();
                        destroy.start();
                        return;*/
                    	 
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
            	Motor.A.setSpeed(SPEED);
            	Motor.B.setSpeed(SPEED); // it adjust the wheels into straightforward position, bc linefollower confused it before
            	Motor.A.forward(); //we set it to move forward just a bit to make sure it gets straight
                Motor.B.forward();
                Delay.msDelay(75);
                //Sound.twoBeeps();
                Motor.A.stop();
                Motor.B.stop();
                //actual avoidance happening
                Motor.A.rotate(-TURN_ANGLE); //turns out sharply so that it can avoid the obstacle
                Delay.msDelay(10); 			//       actual avoidance happening
                Motor.A.setSpeed(SPEED/4);  //sets the wheels for a turning angle 
                Motor.B.setSpeed(SPEED);
                //Motor.A.rotate(-TURN_ANGLE/4);
            }
        //Motor.A.close();
        //Motor.B.close();
        } 
    }
}
