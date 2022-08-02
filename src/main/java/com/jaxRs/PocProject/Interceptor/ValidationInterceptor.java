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
@ValidationInterBinding
public class ValidationInterceptor implements ReaderInterceptor{

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		      InputStream is = context.getInputStream();
		      JsonObject json = Json.createReader(is).readObject();
		      System.out.println("json is" +json);
		      if(json.getString("userName").isBlank() || json.getString("role").isBlank() || json.getString("password").isBlank()) {
				System.out.println("Inside Condition "+json.get("userName"));
				Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("User Name cannot be Empty")
				.build();
				throw new WebApplicationException(response);
			}
		     is = new ByteArrayInputStream(json.toString().getBytes());
		      context.setInputStream(is);
		      System.out.println(is);
		      return context.proceed();
	}

}
