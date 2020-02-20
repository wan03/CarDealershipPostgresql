package test;
import static org.junit.Assert.*;


import org.junit.Test;

import com.revature.CarDealership.DAO.UserSerializationDAO;
import com.revature.CarDealership.pojos.Customer;
import com.revature.CarDealership.pojos.Employee;
import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;

public class UserDAOTest {
	
	UserSerializationDAO DAO = new UserSerializationDAO();

	
	@Test
	public void addCustomerTest() {
		
		Customer testCustomer = new Customer("Test", "TestCustomer", "testing");
		
		DAO.addUser(testCustomer);
		
		Users allUsers = DAO.readAllUsers();
		assertTrue(allUsers.contains(testCustomer));
		
		
	}
	
	@Test
	public void addEmployeeTest() {
		
		Employee testEmployee = new Employee("Test", "TestEmployee", "testing");
		
		DAO.addUser(testEmployee);
		
		Users allUsers = DAO.readAllUsers();
		
			
		assertTrue(allUsers.contains(testEmployee));
		
	}
	
	@Test
	public void removeUserTest () {
		
		
		Users allUsers = DAO.readAllUsers();
		
		for (User u : allUsers) {
			
			if (u.getUserName().equalsIgnoreCase("TestEmployee")) {
				DAO.removeUser(u);
				Users updatedUsers = DAO.readAllUsers();
				assertFalse(updatedUsers.contains(u));
			}
			
		}
		
		
		
	}
	


}
