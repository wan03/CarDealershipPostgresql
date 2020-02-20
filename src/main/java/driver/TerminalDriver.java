package driver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.revature.CarDealership.DAO.CarSerializationDAO;
import com.revature.CarDealership.DAO.UserSerializationDAO;
import com.revature.CarDealership.pojos.Automobiles;
import com.revature.CarDealership.pojos.Car;
import com.revature.CarDealership.pojos.Customer;
import com.revature.CarDealership.pojos.Employee;
import com.revature.CarDealership.pojos.Payment;
import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;

public class TerminalDriver {
	
	static CarSerializationDAO carDAO = new CarSerializationDAO();
	static UserSerializationDAO userDAO = new UserSerializationDAO();
		
	public static void main(String args[]){                       
        String s = "Hello, thank you for visiting our dealer.";  
        Scanner scan = new Scanner(s);  
        scan.close();           
        Scanner start = new Scanner(System.in);  
        Automobiles cars = carDAO.readAllCars();
        Users users = userDAO.readAllUsers();
        
        determineUserType(start, users, cars);
      
        start.close();           
        } 
	
	public static void determineUserType (Scanner scan, Users users, Automobiles cars) {
		
		System.out.println("Are you a Customer or an Employee?");
        String instruction = "Please type c for Customer or e for Employee";
        System.out.println(instruction);  
        String userType = scan.next();
        
        System.out.println(userType);
	        if (userType.equalsIgnoreCase("c")) {       
	        
	        	determineAccountStatus(scan, userType, users, cars);
	        	        	
	        } else if (userType.equalsIgnoreCase("e")) {
	        	
	        	determineAccountStatus(scan, userType, users, cars);
	        	
	        } else {
	        	
	        	determineUserType(scan, users, cars);
	        }
		
		
	}
	
	public static void determineAccountStatus (Scanner scan, String userType, Users users, Automobiles cars) {
		
		String accountQuestion = "Do you have an account with us? Type y or n";		
		System.out.println(accountQuestion);
        String response = scan.next();
        
        if (response.equalsIgnoreCase("y")) {
        	
        	loginProcedure(scan, users, cars, userType);
        
        	
        } else if (response.equalsIgnoreCase("n")) {
        	
        	createUser(scan, userType, users, cars);
        	
        } else {
        	
        	determineAccountStatus(scan, userType, users, cars);
        }
		
		
	}
	
	private static void loginProcedure(Scanner scan, Users users, Automobiles cars, String userType) {
		// TODO Auto-generated method stub
		
		System.out.println("Please enter your username");
		String userName = scan.next();
		System.out.println("Please enter your password");
		String password = scan.next();
		
		
		for (User user : users) {		
			
			
					
			if (user.getUserName().equalsIgnoreCase(userName)) {
				
				if (user.getPassword().equalsIgnoreCase(password)){
					
					if (userType.equals("c")) {
						
						actionsCustomer(scan, users, cars, user);
						
					} else if (userType.equals("e")) {
						actionsEmployee(scan, users, cars, user);
						
					}
				} else {
					System.out.println("Sorry, wrong username or password");
					loginProcedure(scan, users, cars, userType);
				}
				
			} 
			}
			
		
		
				
	}

	public static void createUser (Scanner scan, String userType, Users users, Automobiles cars) {
		
		System.out.println("Answer the following questions to create a new user");
		System.out.println("What is your name?");
		String name = scan.next();
		System.out.println("What is your username?");
		String username = scan.next();
		System.out.println("What is your password?");
		String password = scan.next();
		
		if (userType.equalsIgnoreCase("c")) {
			
			Customer newCustomer = new Customer(name, username, password); 
			userDAO.addUser(newCustomer);
			loginProcedure(scan, users, cars, userType);
			
		} else if (userType.equalsIgnoreCase("e")) {
			
			Employee newEmployee = new Employee(name, username, password);					
			userDAO.addUser(newEmployee);
			loginProcedure(scan, users, cars, userType);
			
		}		
		
	}
	
	public static void actionsCustomer (Scanner scan, Users users, Automobiles cars, User user) {
		
		System.out.println("What would you like to do?");
		System.out.println("Available options are View Lot Cars(vlc), Make an Offer (mf), View Cars that you own (vco),"
				+ " View remaining payments(vp) or exit(exit)");
		String action = scan.next();
		
		switch (action) {
		
		case "vlc":
			
			for (Car car : cars) {
				
				System.out.println(car + "\n");
			}
			actionsCustomer(scan, users, cars, user);
			
			break;
		case "mf": 
			
			makeOffer(scan, users, cars, user);
			
			
			break;
		case "vco": 
			
			for (Car car : cars) {
				
				if (car.getBelongsTo().equals(user.getUserName())) {
					
					
					System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Year: " + car.getYear()  + "\n");
					actionsCustomer(scan, users, cars, user);
				} 
			}
			System.out.println("You currently dont own any cars");
			actionsCustomer(scan, users, cars, user);
			break;
		case "vp":
			for (Car car : cars) {
				
				if (car.getBelongsTo().equals(user.getUserName())) {
					
					Payment payments = car.getPayments();
					System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Remaining Paymets: " + payments.getMonths() + " months" + " Monthly Payment: $" + payments.getPaymentAmount()  + "\n");
					actionsCustomer(scan, users, cars, user);
				} 
			}
			System.out.println("You currently dont own any cars");
			actionsCustomer(scan, users, cars, user);
			break;
		case "exit":
			scan.close();
			break;
		default:
			System.out.println("Try again");
			actionsCustomer(scan, users, cars, user);
		}
		
		
		
	}
	
	private static void makeOffer(Scanner scan, Users users, Automobiles cars, User user) {
		
		for (Car car : cars) {
			
			System.out.println(car + "\n");
		}
		
		System.out.println("Please input VIN number of the car");
		String vin = scan.next();
		System.out.println("Please enter your offer");
		int offer = scan.nextInt();
		for (Car car : cars) {
			
			if (car.getVin().equalsIgnoreCase(vin)) {
				car.addOffers(user.getUserName(), offer);
				carDAO.addCar(car);
				actionsCustomer(scan, users, cars, user);
			} 
		}
		System.out.println("Incorrect VIN number");
		makeOffer(scan, users, cars, user);
		
	}

	public static void actionsEmployee (Scanner scan, Users users, Automobiles cars, User user) {
		
		System.out.println("What would you like to do?");
		System.out.println("Available options are Add a Car (ac), Review Offer (ro), Remove Car (rc), View Payments(vp) or exit(exit)");
		String action = scan.next();
		
switch (action) {
		
		case "ac":
			System.out.println("Answer the questions to create a new car");
			System.out.println("What is the make?");
			String make = scan.next();
			System.out.println("What is the model?");
			String model = scan.next();
			System.out.println("What is the year?");
			String year = scan.next();
			System.out.println("What is the price?");
			double price = scan.nextDouble();
			System.out.println("What is the VIN?");
			String VIN = scan.next();
			Car newCar = new Car(make, model, year, price, VIN);			
			carDAO.addCar(newCar);
			actionsEmployee(scan, users, cars, user);
			break;
		case "ro": 
			
			reviewOffer(scan, users, cars, user);
			
			
			break;
		case "rc": 
			for (Car car : cars) {
				
				System.out.println(car + "\n");
			}
			
			System.out.println("Enter VIN number of car you want to remove");
			String vin = scan.next();
			
			for (Car car : cars) {
				
				if (car.getVin().equalsIgnoreCase(vin)) {
					carDAO.removeCar(car);
				}
				
			}
			actionsEmployee(scan, users, cars, user);
			break;
		case "vp":
			
			for (Car car : cars) {
				
				if (car.getPayments() != null) {
					
			Payment payments = car.getPayments();
			System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Remaining Paymets: " + payments.getMonths() + " months" + " Monthly Payment: $" + payments.getPaymentAmount()  + "\n");
			actionsEmployee(scan, users, cars, user);
			} 			 
			}
			actionsEmployee(scan, users, cars, user);
			
			break;
		case "exit":
			scan.close();
			break;
		default:
			System.out.println("Try again");
			actionsEmployee(scan, users, cars, user);
		}
		
	}

	private static void reviewOffer(Scanner scan, Users users, Automobiles cars, User user) {
		
		int amount;
		String cust;
		for (Car car : cars) {
			
			if (car.getOffers() != null) {
				
				HashMap<String, Integer> offers = car.getOffers();
				Iterator it = offers.entrySet().iterator();
				
				while(it.hasNext()) {
					Map.Entry offer = (Map.Entry)it.next();
					amount = (int) offer.getValue();
					cust = (String) offer.getKey();
					System.out.println("VIN: " + car.getVin() + " Offer: $" + amount  + " Customer: "+ cust + "\n");
				}
				
			}
			
		}
		
		System.out.println("Do you want to accept an offer? (y or n)");
		String res = scan.next();
		
		if (res.equalsIgnoreCase("y")) {
			System.out.println("Please enter VIN");
			String vin = scan.next();
			System.out.println("Please enter customer");{
				String userName = scan.next();
				handlePurchase(scan, users, cars, user, vin, userName);
			}
		} else {
			actionsEmployee(scan, users, cars, user);
		}
	}

	private static void handlePurchase(Scanner scan, Users users, Automobiles cars, User user, String vin, String userName) {
		
		for (Car car : cars) {
			
			if(car.getVin().equals(vin)) {
				
				car.setBelongsTo(userName);
				
				HashMap<String, Integer> offers = car.getOffers();
				Integer offer = offers.get(userName);
				int payment = offer/60;
				
				car.setPayments(60, payment);			
				car.removeOffers();
				carDAO.addCar(car);
				
				
			}
				
			
		}
		
		actionsEmployee(scan, users, cars, user);
		
	}
	
	
		
	
}
