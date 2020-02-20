package com.revature.CarDealership.pojos;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobiles extends ArrayList<Car> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8288765632810724758L;

	
	public void changeOwnership(Car car, String owner) {
		
		if (this.contains(car)) {
			
			this.remove(car);
			car.setBelongsTo(owner);			
			this.add(car);
				
		}
		
	}
	
		

}
