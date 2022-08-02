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
@ItemFormatBinding
public class ItemFormatInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		
		//String itemIdFormat= "[A-Z]{1}{6}";
		InputStream inputStream = context.getInputStream();
		JsonObject json = Json.createReader(inputStream).readObject();
		System.out.println("Json Format is "+json);
		if (!json.getString("itemId").matches("[A-Z]{1}[0-9]{5}")) {
			System.out.println("Inside Condition "+json);
			Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ItemId format is not correct")
					.build();
					throw new WebApplicationException(response);
		}
		
	    inputStream = new ByteArrayInputStream(json.toString().getBytes());
	      context.setInputStream(inputStream);
	      System.out.println(inputStream);
	      return context.proceed();
	}

}
