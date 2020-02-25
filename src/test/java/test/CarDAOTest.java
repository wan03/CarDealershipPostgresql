package test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.CarDealership.pojos.Car;
import com.revature.CarDealership.DAO.CarSerializationDAO;;

public class CarDAOTest {

	CarSerializationDAO DAO = new CarSerializationDAO();
	
	@Test
	public void addCarTest() {
		
		
		Car testCar = new Car("Mercedes Benz", "C300", 2017, 18000.00, 4);
		
		
		DAO.addCar(testCar);
		
		List<Car> allCars = DAO.readAllCars();
		
		
		
		assertTrue(allCars.contains(testCar));
		
		
	}
	

	@Test
	public void changeCarOwnershipTest () {
		
		List<Car> allCars = DAO.readAllCars();
		
		Car currentCar = allCars.get(0);
		
		
		
		DAO.changeCarOwnership(currentCar.getVin(), "dealership", 17000);
		
		currentCar = allCars.get(0);
		
		assertEquals("Check if make is correct", "dealership", currentCar.getBelongsTo());
		
	}
	
	@Test
	public void removeCarTest() {
		
		
		int vin = 1;
			
		
		
		Car carRemove = DAO.selectCarByVIN(vin);
		DAO.removeCar(vin);
		
		List<Car> AllCars = DAO.readAllCars();
		
		assertFalse(AllCars.contains(carRemove));
		
		
	}
 
}
