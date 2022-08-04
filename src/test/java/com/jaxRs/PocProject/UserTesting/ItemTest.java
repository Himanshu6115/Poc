package com.jaxRs.PocProject.UserTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jaxRs.PocProject.Domain.ItemDomain;
import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Interceptor.ItemFormatInterceptor;
import com.jaxRs.PocProject.Interceptor.ItemPriceInterceptor;
import com.jaxRs.PocProject.Repo.UserDatabase;
import com.jaxRs.PocProject.Resource.ItemResource;
import com.jaxRs.PocProject.ServiceImpl.ItemImpl;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

public class ItemTest extends JerseyTest {
	
	  @Override
	    public Application configure() {
	        /*
	         * enable(TestProperties.LOG_TRAFFIC); enable(TestProperties.DUMP_ENTITY);
	         */
	        return new ResourceConfig(ItemResource.class);
	    }
   
    @BeforeEach
    @Disabled
    public void TestProducerRole() {
   	 UserDomain userDomain = new UserDomain(1, "Himanshu", "Producer", "Himanshu@123");
   	 UserDatabase userDatabase = mock(UserDatabase.class);
   	 when(userDatabase.validate("Himanshu", "Himanshu@123")).thenReturn(userDomain);
   	 System.out.println("data is "+userDatabase.validate("Himanshu", "Himanshu@123").getRole());
   	 assertEquals("Producer", userDatabase.validate("Himanshu", "Himanshu@123").getRole());
   	 //verify(userDatabase).validate("Himanshu", "Himanshu@123");
   	 System.out.println("Calling");
    }
   
    @Test
    @Disabled
    public void createItem() {
    	ItemDomain itemDomain = new ItemDomain("A13123", "Desktop Dell", "I5 Processor 8 GB RAM", 5555);
    	Response response = target("/item").request().post(Entity.entity(itemDomain,MediaType.APPLICATION_JSON));
        assertEquals("should return status 201", 201, response.getStatus());
        assertNotNull("Should return user list", response.getEntity());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
    
    @Test
    @Disabled
    public void updateItems() {
    	ItemDomain itemDomain = new ItemDomain("A13123", "Desktop Dell", "I5 Processor 8 GB RAM", 6655);
    	Response response = target("/item").request().put(Entity.entity(itemDomain,MediaType.APPLICATION_JSON));
        assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return user list", response.getEntity());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
    
    @Test
    @Disabled
    public void getItems() {
    	
        Response response = target("/item/A12346").request().get();
        assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return user list", response.getEntity().toString());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
    
  
    @Test
    public void TestItemIdFormat() throws WebApplicationException, IOException {  	
    	ReaderInterceptorContext readerInterceptorContext = mock(ReaderInterceptorContext.class);
    	String itemIdFormatString="[A-Z]{1}[0-9]{5}";
    	ItemFormatInterceptor itemFormatInterceptor = mock(ItemFormatInterceptor.class);
    	when(itemFormatInterceptor.aroundReadFrom(readerInterceptorContext)).thenReturn("A13443".matches(itemIdFormatString));
    	assertEquals("A13443".matches(itemIdFormatString), itemFormatInterceptor.aroundReadFrom(readerInterceptorContext));
    	//verify(itemFormatInterceptor).aroundReadFrom(readerInterceptorContext);
    	
    }
//    
//    @Test
//    public void TestItemPrice() throws WebApplicationException, IOException{
//    	ReaderInterceptorContext readerInterceptorContext = mock(ReaderInterceptorContext.class);
//    	ItemPriceInterceptor itemPriceInterceptor = mock(ItemPriceInterceptor.class);
//    	
//    	
//    }
   
    
//    @Test
//    public void deleteItems() {
//      Response response = target("/item/A83988").request().delete();
//      assertEquals("should return status 200", 200, response.getStatus());
//      assertNotNull("Should return user list", response.getEntity());
//      System.out.println(response.getStatus());
//      System.out.println(response.readEntity(String.class));
//    }
}