package com.revature.CarDealership.pojos;

import java.io.Serializable;

public class User extends Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269612208459571405L;

	private String name;
	
	private String userName;
	
	private String password;
	
	private String userType;
	
	private String indetifier;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getIndetifier() {
		indetifier = this.userName;
		return indetifier;
	}
	
	public User (String name, String userName, String password, String userType) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals (Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	        result = false;
	    } else {
	        User user = (User) object;
	        if (this.userName.equals(user.getUserName())) {
	            result = true;
	        }
	    }
	    return result;
	}

}
