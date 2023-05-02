/*import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class ObstacleDetector extends Thread {
    public static int TURN_ANGLE = 180;
    DataExchange DE = new DataExchange(); 

    public ObstacleDetector(DataExchange DE) {
    	this.DE = DE; 
    }

    public void run() 
    {
        while (!Button.ESCAPE.isDown()) {
        	
                float d = DataExchange.getDistance();

				if (d  < 0.11) //here we use the dataexchange distance value
				{
                    System.out.println("Obstacle detected!");
                    Sound.twoBeeps();
                    Motor.A.stop();
                    Motor.B.stop();
                        Motor.A.rotate(-TURN_ANGLE);
                        Motor.A.forward();
                        Motor.B.forward();
                        Motor.A.rotate(TURN_ANGLE);
                        Motor.A.forward();
                        Motor.B.forward();
                        Delay.msDelay(3000);
                        Motor.A.rotate(-TURN_ANGLE/2);
                    }
            else {
                Motor.A.forward();
                Motor.B.forward();
            }
        }
        Motor.A.close();
        Motor.B.close();
    }
}*/