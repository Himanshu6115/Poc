package com.jaxRs.PocProject.ServiceImpl;

import org.hibernate.loader.custom.Return;

import com.jaxRs.PocProject.Domain.ItemDomain;
import com.jaxRs.PocProject.Repo.ItemDatabase;

import jakarta.ws.rs.core.Response;

public class ItemImpl {
	
	public Response addItems(ItemDomain item) {
		
		ItemDomain itemDomain = new ItemDomain();
		itemDomain.setItemId(item.getItemId());
		itemDomain.setName(item.getName());
		itemDomain.setDescription(item.getDescription());
		itemDomain.setPrice(item.getPrice());
		new ItemDatabase().createEmpl(itemDomain);
		return Response.status(200).entity(itemDomain).build();
	}
	
	public Response updateItem(String itemId,ItemDomain item) {
		ItemDomain itemDomain = new ItemDomain();
		
		itemDomain.setItemId(item.getItemId());
		itemDomain.setName(item.getName());
		itemDomain.setDescription(item.getDescription());
		itemDomain.setPrice(item.getPrice());
		new ItemDatabase().updateItems(itemId,itemDomain);
		return Response.status(200).entity(item).build();
	}
	
	public Response getItemsById(String itemId) {
		
		ItemDomain itemDomain = new ItemDomain();
		itemDomain = new ItemDatabase().getItemDetailsById(itemId);
		return Response.status(200).entity(itemDomain).build();
	}
	
	public Response deleteItem(String itemId) {
		
		ItemDomain itemDomain = new ItemDomain();
		itemDomain = new ItemDatabase().deleteItems(itemId);
		
		return Response.status(200).entity(itemDomain).build();
	}

}
