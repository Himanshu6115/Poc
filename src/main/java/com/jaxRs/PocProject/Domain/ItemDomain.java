package com.jaxRs.PocProject.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemDomain {
	
	@Id
	private String itemId;
	private String name;
	private String description;
	private Integer price;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public ItemDomain(String itemId, String name, String description, Integer price) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public ItemDomain() {
		super();
	}
	
	

}
