public class Main{


	public static void main(String[] args) {

	  Connect connect = new Connect(); 
	  connect.start();
	  
	  
	  DataExchange DE = new DataExchange();
		
	  ColorSensor colorSensor = new ColorSensor(DE); 
      UltrasonicSensor ultraSonic = new UltrasonicSensor(DE); 
      LineFollower lineFollower = new LineFollower(DE);
      
      //should be some type of song thread as well
      
      
      colorSensor.start(); 
      ultraSonic.start();
      lineFollower.start();
      
      
      /*if (Thread.interrupted()) {
    	  Destroy destroy = new Destroy ();
    	  destroy.start();
      }*/
      
	}
}