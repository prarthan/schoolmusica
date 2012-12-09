package com.khatu.musicschool.wsresource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.service.TrackService;
import com.khatu.musicschool.wsresource.response.SchoolReportResponse;

@Component
@Path("/track")
public class SchoolTrack {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TrackService trackService;
	
	/**
	 * track and redirect user to department url
	 * @param id
	 * @param departmentUrl
	 * @param response
	 * @return
	 */
	
	@GET
	@Path("/school")
	public Response trackSchool(@QueryParam("schoolId") int id,@QueryParam("departmentUrl") String departmentUrl,@Context HttpServletResponse response){
		
		try {
			try{
				trackService.trackClick(id);
			}catch (Exception e) {
				logger.error("can not report click for school: {} {}",id,e.getMessage());
			}
			response.sendRedirect(departmentUrl);
		} catch (IOException e) {
			logger.error("can not redirect url : {} {}",departmentUrl,e.getMessage());
		}
		return Response.ok().build();
	}
	
	
	/**
	 * get school report
	 * @param schoolId
	 * @return
	 */
	
	@GET
	@Path("/report/school")
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON })
	public SchoolReportResponse getReport(@QueryParam("schoolId") int schoolId){
		try{
		   return trackService.getReport(schoolId);
		}catch (Exception e) {
			logger.error("can not get report for school : {} {}",schoolId,e.getStackTrace());
			throw new WebApplicationException(400);
		}
	}
}
