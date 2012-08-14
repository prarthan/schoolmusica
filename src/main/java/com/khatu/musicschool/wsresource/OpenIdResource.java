package com.khatu.musicschool.wsresource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.authentication.OpenIdConsumerService;


@Component
@Path("/openid")
public class OpenIdResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OpenIdConsumerService openIdConsumerService;
	
	
	@GET
	@Path("/getToken")
	public void processRequest(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException, ServletException{
		logger.debug("Getting token for "+ request.toString());
		openIdConsumerService.authRequest("",request, response);
		logger.debug("done getting token");
	}
	
	@GET
	@Path("/authenticate")
	public void authenticateUser(@Context HttpServletRequest request){
		logger.info("verified response" + openIdConsumerService.verifyResponse(request));		
	}

}
