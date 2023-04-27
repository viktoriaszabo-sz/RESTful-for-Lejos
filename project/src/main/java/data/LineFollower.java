package data;

import java.io.File;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.utility.Delay;


public class LineFollower extends Thread {
    public static int speed = 300;
    public static final float BLACK_THRESHOLD = 0.1f;
    public static int turn_angle = 220;
    DataExchange DE; 
    public static int max_obstacles = 0; //changed this part of the original program

    public LineFollower(DataExchange DE) { 
    	this.DE = DE; 
    } 

    public void run()
    {
    	System.out.println("Operation WALL-E initiated");
        
    	max_obstacles = 2;
    	int obstacleCount = 0;
    	
        while(!Button.ESCAPE.isDown() || obstacleCount < max_obstacles) 
        {
        	float[] colorSample = DataExchange.colorSample;
            
            if (DataExchange.getCMD() == 1) //LineFollower code if no obstacle is detected
            {
            	if(colorSample [0] == Color.BLACK) {
                	// On the line, move straight
                	Motor.A.setSpeed(speed);
                	Motor.B.setSpeed(speed);
                	Motor.A.forward();
                    Motor.B.forward();
            	} 
           		else {
                	// Off the line, adjust direction
                	if(colorSample[0] < BLACK_THRESHOLD) {
                    	// Too far to the right, turn left
                    	Motor.A.setSpeed(speed / 4);
                    	Motor.B.setSpeed(speed);
                    	Motor.A.forward();
                        Motor.B.forward();
                	} 
                	else if (colorSample[0] > BLACK_THRESHOLD){

                    	// Too far to the left, turn right
                    	Motor.A.setSpeed(speed);
                    	Motor.B.setSpeed(speed / 2);
                    	Motor.A.forward();
                        Motor.B.forward();
                	}
            	}
            }
            else if (DataExchange.getCMD() == 2){ //if obstacle is detected
            	
            	System.out.println("Obstacle detected!");
            	obstacleCount++;
            	if (obstacleCount == max_obstacles) { // stop after the second encounter
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
                    	
                    	File celeb_music = new File ("dis.wav");
                		Sound.playSample(celeb_music, Sound.VOL_MAX);
                		Delay.msDelay(1000);
                		
                		Motor.A.stop();
    					Motor.B.stop();
    					interrupt();
    					//return;
                    }
                }
            	Motor.A.setSpeed(speed);
            	Motor.B.setSpeed(speed); // it adjust the wheels into straightforward position, bc linefollower confused it before
            	Motor.A.forward(); //we set it to move forward just a bit to make sure it gets straight
                Motor.B.forward();
                Delay.msDelay(75);
                //Sound.twoBeeps();
                Motor.A.stop();
                Motor.B.stop();
                //actual avoidance happening
                Motor.A.rotate(-turn_angle); //turns out sharply so that it can avoid the obstacle
                Delay.msDelay(10); 			//       actual avoidance happening
                Motor.A.setSpeed(speed/4);  //sets the wheels for a turning angle 
                Motor.B.setSpeed(speed);
                //Motor.A.rotate(-TURN_ANGLE/4);
            }
        //Motor.A.close();
        //Motor.B.close();
        } 
    }
}