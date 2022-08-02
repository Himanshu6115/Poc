package com.jaxRs.PocProject.ServiceImpl;


import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Exception.UserAlreadyExist;
import com.jaxRs.PocProject.Repo.UserDatabase;

import jakarta.ws.rs.core.Response;
import javassist.expr.NewArray;


public class UserImpl {

	UserDatabase userDatabase = new UserDatabase();
	public Response createUsers(UserDomain userDomain) {
		
		UserDomain user = new UserDomain();
		try {
			user.setUserId(userDomain.getUserId());
			user.setUserName(userDomain.getUserName());
			user.setRole(userDomain.getRole());
			user.setPassword(userDomain.getPassword());
			userDatabase.createEmpl(userDomain);
			
		} catch (Exception ex) {
			throw new UserAlreadyExist("Already Present");
		}
	
		return Response.status(200).entity(user).build();
	}

}
