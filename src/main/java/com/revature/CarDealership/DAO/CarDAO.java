package com.revature.CarDealership.DAO;

import com.revature.CarDealership.pojos.Car;

public interface CarDAO {
	
	public void addCar(Car c);
	
	public void removeCar (Car c);
	
	public Car readCar(String identifier);

}
