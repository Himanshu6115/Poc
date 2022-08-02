package com.jaxRs.PocProject.Resource;

import com.jaxRs.PocProject.Domain.ItemDomain;
import com.jaxRs.PocProject.Interceptor.ItemFormatBinding;
import com.jaxRs.PocProject.Interceptor.ItemPriceInterBinding;
import com.jaxRs.PocProject.Interceptor.ItemValidationInterBinding;
import com.jaxRs.PocProject.Interceptor.ItenDescriptionInterBinding;
import com.jaxRs.PocProject.ReqFilter.ProdConsBindingFilter;
import com.jaxRs.PocProject.ReqFilter.ProducerFilterBinding;
import com.jaxRs.PocProject.ServiceImpl.ItemImpl;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/item")
public class ItemResource {
	
	ItemImpl itemImpl = new ItemImpl();
	
	@POST
	@ProducerFilterBinding
	@ItemFormatBinding
	@ItemValidationInterBinding
	@ItemPriceInterBinding
	@ItenDescriptionInterBinding
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem(ItemDomain itemDomain) {
		return itemImpl.addItems(itemDomain);
	}
	
	@PUT
	@Path("{itemId}/edit")
	@ProducerFilterBinding
	@ItemFormatBinding
	@ItemValidationInterBinding
	@ItemPriceInterBinding
	@ItenDescriptionInterBinding
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItems(@PathParam("itemId")String itemId,ItemDomain itemDomain) {
		return itemImpl.updateItem(itemId,itemDomain);
	}
	
	@GET
	@ProdConsBindingFilter
	@Path("{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems(@PathParam("itemId")String itemId) {
		return itemImpl.getItemsById(itemId);
	}
	
	@DELETE
	@ProducerFilterBinding
	@Path("{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItems(@PathParam("itemId")String itemId) {
		return itemImpl.deleteItem(itemId);
	}
}
