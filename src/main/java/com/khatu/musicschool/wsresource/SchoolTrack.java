package com.khatu.musicschool.wsresource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/track")
public class SchoolTrack {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * track and redirect user to department url
	 * @param id
	 * @param departmentUrl
	 * @param response
	 * @return
	 */
	
	@GET
	public Response trackSchool(@QueryParam("schoolId") int id,@QueryParam("departmentUrl") String departmentUrl,@Context HttpServletResponse response){
		
		try {
			response.sendRedirect(departmentUrl);
		} catch (IOException e) {
			logger.error("can not redirect url : {}",departmentUrl,e.getStackTrace());
		}
		return Response.ok().build();
	}
	
}
