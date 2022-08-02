package com.jaxRs.PocProject.ReqFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Repo.UserDatabase;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;


@Provider
@AdminFilterBinding
public class AdminFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY= "Authorization";
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		System.out.println("Header is "+authHeader);
		if (authHeader!=null && authHeader.size()>0) {
			String authToken = authHeader.get(0);
			System.out.println("Token is "+authToken);
			String decordeString = new String (Base64.getDecoder().decode(authToken.substring(6).getBytes()));
			System.out.println("string of Admin "+decordeString);
			StringTokenizer tokenizer = new StringTokenizer(decordeString,":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
		   
			UserDatabase userDatabase=new  UserDatabase();
//			Boolean userDomain1 = userDatabase.validate(username, password);
//			if (userDomain1!=null && userDomain1.equals(true) ) {
//				return;
//			}
			UserDomain userDomain1 = new UserDomain();
			 userDomain1 = userDatabase.validate(username,password);
			 if (userDomain1!=null && userDomain1.getRole().equalsIgnoreCase("Admin") ) {
				return;
			}
	Response UnAutorizedResource = Response.status(Response.Status.UNAUTHORIZED)
			.entity("User is not Authorized to the Application")
			.build();
	requestContext.abortWith(UnAutorizedResource);
            }
      }
}
