package main;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class CRUDClient {
	
	private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUDAssignGradeBookService/gradebook";
	
    public CRUDClient() {
		super();
		ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
	}
	
    public ClientResponse updateGrade(String id, Object xmlpayload){
    	webResource = client.resource(BASE_URI).path("updategrade");
    	ClientResponse response = webResource.path(id).type(MediaType.APPLICATION_XML).put(ClientResponse.class, xmlpayload);
    	return response;
    }
    
    public ClientResponse addGrade(String id, String item, Object xmlpayload){
    	webResource = client.resource(BASE_URI).path("addgrade");
    	ClientResponse response = webResource.path(id).path(item).type(MediaType.APPLICATION_XML).put(ClientResponse.class, xmlpayload);
    	return response;
    }
    
    public ClientResponse addStudent(Object xmlpayload){
    	webResource = client.resource(BASE_URI).path("addstudent");
    	ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, xmlpayload);
    	return response;
    }
    
    public ClientResponse addWorkItem(Object xmlpayload){
    	webResource = client.resource(BASE_URI).path("addworkitem");
    	ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, xmlpayload);
    	return response;
    }
    public ClientResponse viewGrade(String id, String itemname){
    	webResource = client.resource(BASE_URI).path("viewgrade");
    	ClientResponse response = webResource.path(id).path(itemname).get(ClientResponse.class);
    	return response;
    }
    
    public ClientResponse deleteGrade(String id, String itemname){
    	webResource = client.resource(BASE_URI).path("deletegrade");
    	ClientResponse response = webResource.path(id).path(itemname).delete(ClientResponse.class);
    	return response;
    }
    
}
