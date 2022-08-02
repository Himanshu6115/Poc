package com.jaxRs.PocProject.Resource;

import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Interceptor.PasswordInterBinding;
import com.jaxRs.PocProject.Interceptor.ValidationInterBinding;
import com.jaxRs.PocProject.ReqFilter.AdminFilterBinding;
import com.jaxRs.PocProject.ServiceImpl.UserImpl;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/data")
public class UserResource {
	
	UserImpl userImpl = new UserImpl();

	@POST
	@PasswordInterBinding
	@AdminFilterBinding
	@ValidationInterBinding
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsers(UserDomain userDomain) {
		return userImpl.createUsers(userDomain);
	}

}
