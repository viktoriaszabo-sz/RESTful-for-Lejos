public class Main{


	public static void main(String[] args) {

		DataExchange DE = new DataExchange();
		
		Connect conn = new Connect(DE); //the thread that reads values from the web service database
		
	  ColorSensor colorSensor = new ColorSensor(DE); 
      UltrasonicSensor ultraSonic = new UltrasonicSensor(DE); 
      LineFollower lineFollower = new LineFollower(DE);
      
      conn.start();
    try {
		conn.join(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
      
      colorSensor.start(); 
      ultraSonic.start();
      lineFollower.start();
      
	}
}