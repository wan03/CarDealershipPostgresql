package com.revature.CarDealership.pojos;

//import com.revature.CarDealership.DAO.CarSerializationDAO;

public class Customer extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 449667313234502584L;

	public Customer(String name, String userName, String password) {
		super();
		this.setName(name);
		this.setUserName(userName);
		this.setPassword(password);
		this.setUserType("customer");
	}


	
	

	
}
 