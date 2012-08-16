package com.khatu.musicschool.wsresource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.model.Program;
import com.khatu.musicschool.model.Style;
import com.khatu.musicschool.service.ProgramService;

@Component
@Path("/program")
public class ProgramResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProgramService programService;
	
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON })
	public List<Program> getAllProgram(){
		return programService.getAllProgram();
	}
	
	@GET
	@Path("/style/{ids}")
	@Produces({MediaType.APPLICATION_JSON })
	public List<Style> getStyles(@PathParam("ids") String ids){
		List<Style> styles = new ArrayList<Style>();
		try{
			List<Integer> instrumentIds = new ArrayList<Integer>();
			List<String> instrumentIdstring = Arrays.asList(ids.split(","));
			for(String currentId:instrumentIdstring){
				instrumentIds.add(Integer.parseInt(currentId));
			}
			styles = programService.getAllStylesById(instrumentIds);
		}catch(Exception e){
			
			logger.error("can not get styles",e.getStackTrace());
		}
		return styles;
	}

}
