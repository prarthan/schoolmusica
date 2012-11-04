package com.khatu.musicschool.wsresource;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.authentication.OpenIdServiceManager;
import com.khatu.musicschool.service.AuthenticationService;


@Component
@Path("/openid")
public class OpenIdResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OpenIdServiceManager openIdServicemanager;
	
	@Autowired
	private AuthenticationService authnticateService;
	


	private static final long ONE_HOUR = 3600000L;
    private static final long TWO_HOUR = ONE_HOUR * 2L;
    private static final String ATTR_MAC = "openid_mac";
    private static final String ATTR_ALIAS = "openid_alias";
    
	
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public void authenticate(@QueryParam ("op") String op,@Context HttpServletRequest request,@Context HttpServletResponse response){
		
	   if(op !=null){
	        Association association = openIdServicemanager.getAssociation();
	        request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
	        request.getSession().setAttribute(ATTR_ALIAS, openIdServicemanager.getEndpoint().getAlias());
	        String url = openIdServicemanager.getManager().getAuthenticationUrl(openIdServicemanager.getEndpoint(), association);
	        try {
	        	
				response.sendRedirect(url);
			} catch (IOException e) {
				logger.error("can not redirect to open Id provider",e.getStackTrace());
			}
	   }else{
		   validatetest(request,response); 
       	   logger.info("I am here");
	   }

	}
	
	
public void validatetest(HttpServletRequest request,HttpServletResponse response){
		
		String url = "http://www.schoolmusica.com/school.jsp";
		try{
			HttpSession session = request.getSession();       
		    byte[] mac_key = (byte[]) session.getAttribute(ATTR_MAC);       
		    String alias = (String) session.getAttribute(ATTR_ALIAS);  
		    logger.info("authenticated user");
		    Authentication authentication = openIdServicemanager.getManager().getAuthentication(request, mac_key, alias);       
		    String identity = authentication.getIdentity();       
		    String email = authentication.getEmail(); 
			
		    logger.info("user email id"+ email);
		    
		    int schoolId = authnticateService.authenticateAndGetSchool(email);
		    response.addCookie(getCookie(email));
		    response.addCookie(getTestCookie());
		    session.setAttribute("email", email);
		    logger.info("added cookie to response");
		    response.sendRedirect(url+"?id="+schoolId);
			} catch (IOException e) {
				logger.error("can not validate user.",e.getStackTrace());
			}

				
	}
	
	@GET
	@Path("/validate")
	public void validate(@Context HttpServletRequest request,@Context HttpServletResponse response){
		
		String url = "http://www.schoolmusica.com/school.jsp";
		try {
			HttpSession session = request.getSession();       
		    byte[] mac_key = (byte[]) session.getAttribute(ATTR_MAC);       
		    String alias = (String) session.getAttribute(ATTR_ALIAS);  
		    logger.info("authenticated user");
		    Authentication authentication = openIdServicemanager.getManager().getAuthentication(request, mac_key, alias);       
		    String identity = authentication.getIdentity();       
		    String email = authentication.getEmail(); 
			
		    logger.info("user email id"+ email);
		    
		    int schoolId = authnticateService.authenticateAndGetSchool(email);
		    response.addCookie(getCookie(email));
		    response.addCookie(getTestCookie());
		    session.setAttribute("email", email);
		    logger.info("added cookie to response");
			response.sendRedirect(url+"?id="+schoolId);
		} catch (IOException e) {
			logger.error("can not validate user.",e.getStackTrace());
		}
				
	}
	
	/**
	 * set cookie with school musica user email
	 * @param email
	 * @return
	 */
	private Cookie getCookie(final String email){
		 Cookie ck = new Cookie("schoolmusicausermail", email);
		 return ck;
	}
	
	private Cookie getTestCookie(){
		Cookie ck = new Cookie("siteMail", "schoolmusica");
		return ck;
	}
	

}
