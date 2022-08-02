package com.jaxRs.PocProject.UserTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;

import com.jaxRs.PocProject.Domain.ItemDomain;
import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Interceptor.PasswordInterceptor;
import com.jaxRs.PocProject.Resource.ItemResource;
import com.jaxRs.PocProject.Resource.UserResource;
import com.jaxRs.PocProject.ServiceImpl.UserImpl;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

class UserTest extends JerseyTest {
	
    @Override
    public Application configure() {
        /*
         * enable(TestProperties.LOG_TRAFFIC); enable(TestProperties.DUMP_ENTITY);
         */
        return new ResourceConfig(ItemResource.class);
    }

//	@Test
//	public void createUsers() {
////		UserDomain userDomain = new UserDomain();
////		UserImpl userImpl = new UserImpl();		
//		UserDomain userDomain = new UserDomain(10, "Bipin", "Producer", "Bipin@1234");
//		UserImpl userImpl = new UserImpl();
//		UserResource userResource = mock(UserResource.class);
//		when(userResource.createUsers(userDomain)).thenReturn(userImpl.createUsers(userDomain));
//	        Response response = target("/data").request().post(Entity.entity(userDomain, MediaType.APPLICATION_JSON));
//	        assertEquals("should return status 200", 200, response.getStatus());
//	        //assertNotNull("Should return user list", response.getEntity());
//	        System.out.println(response.getStatus());
//	      //  System.out.println(response.readEntity(String.class));
//	}
	
//	ReaderInterceptorContext readerInterceptorContext = mock(ReaderInterceptorContext.class);
//	
//	@Test
//	public void passwordTest() throws WebApplicationException, IOException {
//		
//		String regex_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,24}$";
//		UserDomain userDomain = new UserDomain(12,"Mayank","Producer","Mayank@123");
//		PasswordInterceptor passwordInterceptor = mock(PasswordInterceptor.class);
//		when(passwordInterceptor.aroundReadFrom(readerInterceptorContext)).thenReturn(readerInterceptorContext);
//		assertEquals("Mayank@123", regex_pattern);
//		
//	}
    
    @Test
    public void getItems() {
    	
        Response response = target("/item/A12346").request().get();
        assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return user list", response.getEntity().toString());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}
