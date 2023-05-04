import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.Button;

public class Connect extends Thread {
    
	DataExchange DE;
    
    public Connect(DataExchange DE) { 
    	this.DE = DE;
    }
    public static int value1I;
    public static int value2I;
    public static int value3I;
    
    public void run() {
        System.out.println("Read some text from URL\n");
        //System.out.println("Press any key to start");

        //Button.waitForAnyPress();

        String s = null;

        while (!Button.ESCAPE.isDown()) {
            try {
                URL url = new URL("http://192.168.0.108:8080/rest/project/read_by_lego/42");

                HttpURLConnection conn = null;
                InputStream is = null;
                InputStreamReader isr = null;
                BufferedReader br = null;

                try {
                    conn = (HttpURLConnection) url.openConnection();
                    is = conn.getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);

                    s = br.readLine();
                    String[] values = s.split(" ");
                    String value1S = values[0];
                    String value2S = values[1];
                    String value3S = values[2];

                    value1I = Integer.parseInt(value1S);
                    value2I = Integer.parseInt(value2S);
                    value3I = Integer.parseInt(value3S);

                    /*System.out.println(value1I + " " + value2I + " " + value3I);

                    	DE.setSpeed(value1I);
                        DE.setTurnangle(value2I);
                        DE.setMaxobs(value3I);
                    
                        System.out.println(DE.getSpeed() + " " + DE.getTurnangle() + " " + DE.getMaxobs());
                    */
                    //DataExchange DE = new DataExchange();
                    
                    DE.setSpeed(value1I);
                    DE.setTurnangle(value2I);		//this part is SHITE
                    DE.setMaxobs(value3I);
                    
                    //System.out.println(DE.getSpeed() + " " + DE.getTurnangle() + " " + DE.getMaxobs());
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Some problem!");
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isr != null) {
                        try {
                            isr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some problem!");
            }
            try {
            	Thread.sleep(2000);
	        } catch (InterruptedException e1) {
	            e1.printStackTrace();
	        }
        } // while loop   
    }

	
}