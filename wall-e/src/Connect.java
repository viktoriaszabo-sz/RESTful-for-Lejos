import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
/*import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;*/



import lejos.hardware.Button;
//import runnables;

public class Connect extends Thread {	

	public Connect() {};
	
	public void run(){
		
        System.out.println("Read some text from URL\n");
        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();

        URL url = null;
  		HttpURLConnection conn = null;
  		InputStreamReader isr = null;
  		BufferedReader br=null;

  		String s=null;
		try {
			
			url = new URL("http://127.0.0.1:8080/rest/project/read_ev");

			conn = (HttpURLConnection)url.openConnection();
  			//System.out.println(conn.toString()); //prints out only the url
			
  			if (conn==null) {
	  			System.out.println("No connection!!!");
			}
  			
			InputStream is=null;
			
			try {
				is=conn.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			//e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			
			isr = new InputStreamReader(is); 
      		br=new BufferedReader(isr);
			while ((s=br.readLine())!=null){
				System.out.println(s);
			}
		}
  		catch(Exception e) {
  			e.printStackTrace();
            System.out.println("Some problem!");
  		}
		 finally {
	            try {
	                if (br != null) {
	                    br.close();
	                }
	                if (isr != null) {
	                    isr.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		 }
        System.out.println("Press any key to FINISH");
        Button.waitForAnyPress();
	}
}


		


