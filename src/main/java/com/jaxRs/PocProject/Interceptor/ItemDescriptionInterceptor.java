package com.jaxRs.PocProject.Interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;


@Provider
@ItenDescriptionInterBinding
public class ItemDescriptionInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		
	      InputStream is = context.getInputStream();
	      JsonObject json = Json.createReader(is).readObject();
	      System.out.println("json is" +json);
	      if(json.getString("description").length()>50) {
			System.out.println("Inside Condition "+json.get("description"));
			Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity("Description length cannot exceed to more then 50 character")
			.build();
			throw new WebApplicationException(response);
		}
	     is = new ByteArrayInputStream(json.toString().getBytes());
	      context.setInputStream(is);
	      System.out.println(is);
	      return context.proceed();
	}

}
