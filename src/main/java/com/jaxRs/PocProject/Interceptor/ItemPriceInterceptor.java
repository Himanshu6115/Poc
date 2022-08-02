package com.jaxRs.PocProject.Interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;


@Provider
@ItemPriceInterBinding
public class ItemPriceInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		
		InputStream is = context.getInputStream();
		JsonObject json = Json.createReader(is).readObject();
		System.out.println("json is "+json);
		if (json.getInt("price")>10000 || json.getInt("price")<0) {
			Response response = Response.status(404).entity("The Price Range is not Correct").build();
			throw new WebApplicationException(response);
		}
	     is = new ByteArrayInputStream(json.toString().getBytes());
	      context.setInputStream(is);
	      System.out.println(is);
	      return context.proceed();
	}

}
