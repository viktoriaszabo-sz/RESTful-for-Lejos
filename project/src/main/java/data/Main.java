package data;

import services.Walle; //class from another package

public class Main{


	public static void main(String[] args) {

	  Walle walle = new Walle();
		
	  DataExchange DE = new DataExchange();
		
	  ColorSensor colorSensor = new ColorSensor(DE); 
      UltrasonicSensor ultraSonic = new UltrasonicSensor(DE); 
      LineFollower lineFollower = new LineFollower(DE);
      
      //should be some type of song thread as well
      
      walle.start();
      colorSensor.start(); 
      ultraSonic.start();
      lineFollower.start();
      
      
      //thread2 -- sending stuff back to db
      
      /*if (Thread.interrupted()) {
    	  Destroy destroy = new Destroy ();
    	  destroy.start();
      }*/
      
	}
}