package com.khatu.musicschool.authentication;

import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Component
public class OpenIdServiceManager {
	
	/**
	 * Open Id Manager
	 */
	private final OpenIdManager manager = new OpenIdManager();
	
	
	/**
	 * set redirect and return urls
	 */
	private OpenIdServiceManager(){
		  manager.setRealm("http://www.schoolmusica.com/"); // change to your domain
		  manager.setReturnTo("http://www.schoolmusica.com/rest/openid/validate"); // change to your servlet url   
	}
	
	public OpenIdManager getManager() {
		return manager;
	}
	
	public Endpoint getEndpoint() {
		return manager.lookupEndpoint("Google");
	}

	public Association getAssociation(){
		return manager.lookupAssociation(getEndpoint());
	}
	
	public void logout(){
		Client client = Client.create();
		
		WebResource webResource = client
				   .resource("https://www.google.com/accounts/Logout");
		 
				ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
	}

}
