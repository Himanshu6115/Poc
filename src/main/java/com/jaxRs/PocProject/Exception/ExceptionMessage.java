package com.jaxRs.PocProject.Exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionMessage implements ExceptionMapper<UserAlreadyExist> {

	@Override
	public Response toResponse(UserAlreadyExist exception) {
		
		ErrorMessage errorMessage = new ErrorMessage("User ID Already Exist", 404);
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
