package data;

import services.Walle; //class from another package

public class Main{


	public static void main(String[] args) {

	  Walle walle = new Walle();
	//if walle thread runs, execute the rest of the code
	  
	  
	  DataExchange DE = new DataExchange();
		
	  ColorSensor colorSensor = new ColorSensor(DE); 
      UltrasonicSensor ultraSonic = new UltrasonicSensor(DE); 
      LineFollower lineFollower = new LineFollower(DE);
      
      
      //if html form revieces some info and sends it into add_ev3 / printev3.jsp 
      //execute the threads
      //or if database recieves data --> start the threads
      
      walle.start();
      try {
		walle.join(); 					//the program waits until this thread dies / executes
	} catch (InterruptedException e) {	//and only then it starts doing all the other stuff
		e.printStackTrace();
	}
      //first start wall-e thread
      
      
      colorSensor.start(); 
      ultraSonic.start();
      lineFollower.start();
      
      
      //thread2 -- sending stuff back to db - or maybe this in the linefollower
      //obstacle avoidance part 
      
      
      
      /*if (Thread.interrupted()) {
    	  Destroy destroy = new Destroy ();
    	  destroy.start();
      }*/
      
	}
}