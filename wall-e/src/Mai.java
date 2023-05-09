public class Mai{


	public static void main(String[] args) {

		DataExchange DE = new DataExchange();
		
		Connect conn = new Connect(DE);
		
	  ColorSensor colorSensor = new ColorSensor(DE); 
      UltrasonicSensor ultraSonic = new UltrasonicSensor(DE); 
      LineFollower lineFollower = new LineFollower(DE);
      //should be some type of song thread as well
      //DE.start();
      
      conn.start();
    try {
		conn.join(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
      
      colorSensor.start(); 
      ultraSonic.start();
      lineFollower.start();
      
      /*if (Button.ESCAPE.isDown())
      {
    	  conn.interrupt();
    	  colorSensor.interrupt();
    	  ultraSonic.interrupt();
    	  lineFollower.interrupt();
      }
      */
      
      //we can put an if statement with the button down for better closing for threads
      
      /*if (Thread.interrupted()) {
    	  Destroy destroy = new Destroy ();
    	  destroy.start();
      }*/
      
	}
}