package project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import data.Attri;
import lejos.hardware.Button;
//import runnables;

public class html_connection {

	public static void main(String[] args) {
		
        System.out.println("Read some text from URL\n");
        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();

        URL url = null;
  		HttpURLConnection conn = null;
  		InputStreamReader isr = null;
  		BufferedReader br=null;

  		String s=null;
		try {
			
			url = new URL("http://127.0.0.1:8080/rest/project/add_ev");

			conn = (HttpURLConnection)url.openConnection();
  			System.out.println(conn.toString()); //Tulostaa vain URLin
			
  			if (conn==null) {
	  			System.out.println("No connection!!!");
			}
  			
			InputStream is=null;
			try {
				is=conn.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
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
        System.out.println("Press any key to FINISH");
        Button.waitForAnyPress();
        
        
        try {
        	
        	String urlString = url.toString(); //we convert to previous "add_ev" link to a String for futher use

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlString); //this is where we use the converted urlString
            Builder builder = target.request();
            
            // Set any additional headers or query parameters here, if needed
            // builder.header("X-Custom-Header", "Value");
            // builder.queryParam("param", "value");
            
            // If you need to send form parameters with the request, create a Form object and use the form() method
            Form form = new Form().param("speed", "speed").param("turnangle", "turnangle").param("maxobs", "maxobs").param("securitydis", "securitydis");
            builder.post(Entity.form(form));
            
            Attri exchangeAttri = builder.get(Attri.class);
            String jsonString = builder.get(String.class);
            
            //System.out.println("Data Exchange Object: " + exchangeData);
            //System.out.println("JSON String: " + jsonString);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
	}

}