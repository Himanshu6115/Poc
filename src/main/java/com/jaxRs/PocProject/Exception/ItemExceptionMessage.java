package com.jaxRs.PocProject.Exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ItemExceptionMessage implements ExceptionMapper<ItemAlreadyExist> {

	@Override
	public Response toResponse(ItemAlreadyExist exception) {
		
		ErrorMessage errorMessage = new ErrorMessage("ItemId Already Exist with this name", 404);
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
