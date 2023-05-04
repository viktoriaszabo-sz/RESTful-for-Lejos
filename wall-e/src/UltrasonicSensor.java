import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

// Testing code owner stuff

public class UltrasonicSensor extends Thread {

	public static float distance;
	public static float securityDistance = 9;
	public EV3UltrasonicSensor sonicSensor;
	DataExchange DE = new DataExchange();

    public UltrasonicSensor(DataExchange DE) {
       sonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
       this.DE = DE; 
    }

    public void run() {

    	while (!Button.ESCAPE.isDown()) 
        {
    		SampleProvider sp = sonicSensor.getDistanceMode();
            float[] ultraSample = new float[sp.sampleSize()];
        	sp.fetchSample(ultraSample, 0);
        	float distance = (int)(ultraSample[0]*100);

            //System.out.println("Distance: " + distance);
            
            if(distance > securityDistance)
            {
				//sends message to dataExchange to do command 1 (a.k.a. no obstacle detected)
				DataExchange.setCMD(1);
			}
            else 
			{ //if <= security distance
				
				DataExchange.setCMD(2); //do obstacle avoidance 
			}
        }
        sonicSensor.close();
        Motor.A.close();
        Motor.B.close();
    }
} 
