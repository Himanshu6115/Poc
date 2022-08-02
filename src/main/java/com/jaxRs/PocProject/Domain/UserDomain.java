package com.jaxRs.PocProject.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDomain {
	
	@Id
	private Integer userId;
	private String userName;
	private String role;
	private String password;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDomain(Integer userId, String userName, String role, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.password = password;
	}
	public UserDomain() {
		super();
	}

}
