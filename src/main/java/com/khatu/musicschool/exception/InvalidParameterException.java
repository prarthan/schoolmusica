package com.khatu.musicschool.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidParameterException extends WebApplicationException{

		/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
		private String errorMessage;

		public InvalidParameterException(String errorMessage) {
			super(Response.status(400)
		             .entity(errorMessage).type(MediaType.APPLICATION_JSON).build());
		}
		
		@Override
		public String toString() {
			return "InvalidParameterException [errorMessage=" + errorMessage
					+ "]";
		}

}
