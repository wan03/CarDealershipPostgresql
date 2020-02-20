package com.revature.CarDealership.pojos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Car extends Automobiles implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8692202001251557485L;

	private String make;
	
	private String model;
	
	private String year;
	
	private double price;
	
	private String vin;
	
	private String identifier;
	
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIdentifier() {
		
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
	
	public Car (String make, String model, String year, double price, String vin) {
		super();
		this.make =make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.vin = vin;
		this.belongsTo = "Dealership";
		this.offers.put("none", 0);
	}
	
	public Car () {
		super();
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	@Override
	public boolean equals (Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	        result = false;
	    } else {
	        Car car = (Car) object;
	        if (this.vin.equals(car.getVin())) {
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
