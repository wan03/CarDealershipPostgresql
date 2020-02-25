package com.revature.CarDealership.pojos;

import java.io.Serializable;
import java.util.HashMap;

public class Car extends Automobiles implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8692202001251557485L;

	private String make;
	
	private String model;
	
	private int year;
	
	private double price;
	
	private int vin;
	
	private int identifier;
	
	private String belongsTo;
	
	private HashMap<String, Integer> offers = new HashMap<String, Integer>();
	
	private Payment payment;
	
	public Payment getPayments() {
		return payment;
	}
	
	public void setPayments(int months, int payment) {
		this.payment = new Payment(months, payment);
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIdentifier() {
		
		identifier = getVin();
		return identifier;
	}
	
	public String getBelongsTo() {
		return belongsTo;
	}
	
	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}
	
	@Override
	public String toString() {
		String p = String.valueOf(price);
		return make + " " + model + " " + year + " " + p + " " + vin;
	}
	
	public Car (String make, String model, int year, double price, int vin, String belongsTo) {
		super();
		this.make =make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.vin = vin;
		this.belongsTo = belongsTo;
	}
	
	public Car (String make, String model, int year, double price) {
		super();
		this.make =make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.belongsTo = "dealership";
	}
	
	public Car () {
		super();
	}

	public int getVin() {
		return vin;
	}

	public void setVin(int vin) {
		this.vin = vin;
	}
	
	@Override
	public boolean equals (Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	        result = false;
	    } else {
	        Car car = (Car) object;
	        if (this.vin == (car.getVin())) {
	            result = true;
	        }
	    }
	    return result;
	}

	public HashMap<String, Integer> getOffers() {
		return offers;
	}

	public void addOffers(String userName, int offer) {
		offers.put(userName, offer);
	}
	
	public void removeOffers() {
		offers.clear();
	}



}
