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
		
		
		Car testCar = new Car("Mercedes Benz", "E350", 2017, 32000.00, 4, "dealership");
		
		
		DAO.addCar(testCar);
		
		List<Car> allCars = DAO.readAllCars();
		
		
		
		assertTrue(allCars.contains(testCar));
		
		
	}
	

	@Test
	public void changeCarOwnershipTest () {
		
				
		
		DAO.changeCarOwnership(4, "dealership", 17000);
		
		Car currentCar = DAO.selectCarByVIN(4);
		
		assertEquals("Check if owner is correct", "dealership", currentCar.getBelongsTo());
		
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
