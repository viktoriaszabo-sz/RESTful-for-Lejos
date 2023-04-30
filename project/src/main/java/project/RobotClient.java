/*package project;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import data.DataExchange;

public class RobotClient {

    public static void main(String[] args) {
        String uri = "http://127.0.0.1:8080/rest/project/add_ev";

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(uri);
            Builder builder = target.request();
            
            // Set any additional headers or query parameters here, if needed
            // builder.header("X-Custom-Header", "Value");
            // builder.queryParam("param", "value");
            
            // If you need to send form parameters with the request, create a Form object and use the form() method
            Form form = new Form().param("speed", "speed").param("turnangle", "turnangle").param("maxobs", "maxobs").param("securitydis", "securitydis");
            builder.post(Entity.form(form));
            
            DataExchange exchangeData = builder.get(DataExchange.class);
            String jsonString = builder.get(String.class);
            
            //System.out.println("Data Exchange Object: " + exchangeData);
            //System.out.println("JSON String: " + jsonString);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
*/