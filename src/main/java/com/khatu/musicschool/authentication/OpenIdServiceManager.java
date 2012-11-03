package com.khatu.musicschool.authentication;

import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.springframework.stereotype.Component;


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

}
