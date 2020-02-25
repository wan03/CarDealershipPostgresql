package driver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.CarDealership.DAO.CarSerializationDAO;
import com.revature.CarDealership.DAO.UserSerializationDAO;
import com.revature.CarDealership.pojos.Car;
import com.revature.CarDealership.pojos.Customer;
import com.revature.CarDealership.pojos.Offer;
import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;

public class TerminalDriver {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	static CarSerializationDAO carDAO = new CarSerializationDAO();
	static UserSerializationDAO userDAO = new UserSerializationDAO();
		
	public static void main(String args[]){                       
        Scanner start = new Scanner(System.in);  
        System.out.println("Hello, thank you for visiting our dealer.");            
             
        initialScreen(start);
      
        start.close();           
        } 
	
	public static void initialScreen(Scanner scan) {
		
//		System.out.println("Are you a Customer or an Employee?");
//        String instruction = "Please type c for Customer or e for Employee";
//        System.out.println(instruction);  
//        String userType = scan.next();
		List<Car> cars = carDAO.readAllCars();
        Users users = userDAO.readAllUsers();
		
        
        determineAccountStatus(scan, users, cars);
        
//        System.out.println(userType);
//	        if (userType.equalsIgnoreCase("c")) {       
//	        
//	        	determineAccountStatus(scan, userType, users, cars);
//	        	        	
//	        } else if (userType.equalsIgnoreCase("e")) {
//	        	
//	        	determineAccountStatus(scan, userType, users, cars);
//	        	
//	        } else {
//	        	
//	        	determineUserType(scan, users, cars);
//	        }
		
		
	}
	
	public static void determineAccountStatus (Scanner scan, Users users, List<Car> cars) {
		
		String accountQuestion = "Do you have an account with us? Type y or n";		
		System.out.println(accountQuestion);
        String response = scan.next();
        
        if (response.equalsIgnoreCase("y")) {
        	
        	loginProcedure(scan, users, cars);
        
        	
        } else if (response.equalsIgnoreCase("n")) {
        	
        	createUser(scan, users, cars);
        	
        } else {
        	
        	determineAccountStatus(scan, users, cars);
        }
		
		
	}
	
	private static void loginProcedure(Scanner scan, Users users, List<Car> cars) {
		
		
		System.out.println("Please enter your username");
		String userName = scan.next();
		System.out.println("Please enter your password");
		String password = scan.next();
		
		
		User user = userDAO.selectUser(userName);
		
		
		if (user.getPassword().equalsIgnoreCase(password)){
			
			if (user.getUserType().equalsIgnoreCase("customer")) {
				
				actionsCustomer(scan, users, cars, user);
				
			} else if (user.getUserType().equalsIgnoreCase("employee")) {
				actionsEmployee(scan, users, cars, user);
				
			}
		} else {
			System.out.println("Sorry, wrong username or password");
			loginProcedure(scan, users, cars);
		}
		
		
		
		
//		for (User user : users) {		
			
//			
//					
//			if (user.getUserName().equalsIgnoreCase(userName)) {
//				
//				if (user.getPassword().equalsIgnoreCase(password)){
//					
//					if (userType.equals("c")) {
//						
//						actionsCustomer(scan, users, cars, user);
//						
//					} else if (userType.equals("e")) {
//						actionsEmployee(scan, users, cars, user);
//						
//					}
//				} else {
//					System.out.println("Sorry, wrong username or password");
//					loginProcedure(scan, users, cars, userType);
//				}
//				
//			} 
//			}
			
		
		
				
	}

	public static void createUser (Scanner scan, Users users, List<Car> cars) {
		
		System.out.println("Answer the following questions to create a new user");
		System.out.println("What is your username?");
		String username = scan.next();
		System.out.println("What is your password?");
		String password = scan.next();
		
//		if (userType.equalsIgnoreCase("c")) {
			
			Customer newCustomer = new Customer(username, password); 
			userDAO.addUser(newCustomer);
			determineAccountStatus(scan, users, cars);
//			loginProcedure(scan, users, cars);
			
//		} else if (userType.equalsIgnoreCase("e")) {
			
//			Employee newEmployee = new Employee(username, password);					
//			userDAO.addUser(newEmployee);
//			loginProcedure(scan, users, cars, userType);
			
//		}		
		
	}
	
	public static void actionsCustomer (Scanner scan, Users users, List<Car> cars, User user) {
		
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
			
			List<Car> ownedCars =  carDAO.getOwnedCars(user.getUserName());
			
			if (ownedCars.isEmpty()) {
				
				System.out.println("You currently dont own any cars");
				actionsCustomer(scan, users, cars, user);
				
			} else {
				
				for (Car car : ownedCars) {
					System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Year: " + car.getYear()  + "\n");
					actionsCustomer(scan, users, cars, user);
					
				}
			}
			
//			for (Car car : cars) {
//				
//				if (car.getBelongsTo().equals(user.getUserName())) {
//					
//					
//					System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Year: " + car.getYear()  + "\n");
//					actionsCustomer(scan, users, cars, user);
//				} 
//			}
//			System.out.println("You currently dont own any cars");
//			actionsCustomer(scan, users, cars, user);
			break;
		case "vp":
			viewPayments(scan, users, cars, user);
			
//			for (Car car : cars) {
//				
//				
//				
//				if (car.getBelongsTo().equals(user.getUserName())) {
//					
//					Payment payments = car.getPayments();
//					System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Remaining Paymets: " + payments.getMonths() + " months" + " Monthly Payment: $" + payments.getPaymentAmount()  + "\n");
//					actionsCustomer(scan, users, cars, user);
//				} 
//			}
//			System.out.println("You currently dont own any cars");
//			actionsCustomer(scan, users, cars, user);
			break;
		case "exit":
			initialScreen(scan);
			break;
		default:
			System.out.println("Try again");
			actionsCustomer(scan, users, cars, user);
		}
		
		
		
	}
	
	public static void viewPayments(Scanner scan, Users users, List<Car> cars, User user) {
		
		List<Car> ownedCars =  carDAO.getOwnedCars(user.getUserName());
		
		if (ownedCars.isEmpty()) {
			
			System.out.println("You currently dont own any cars");
			actionsCustomer(scan, users, cars, user);
			
		} else {
			
			for (Car car : ownedCars) {
				double payment = car.getPrice()/60;
				System.out.println("Make: " + car.getMake() + " Model: " + car.getModel() + " Remaining Paymets: 60 months" + " Monthly Payment: $" + df2.format(payment)  + "\n");
				actionsCustomer(scan, users, cars, user);
				
			}
		}
	}
	
	private static void makeOffer(Scanner scan, Users users, List<Car> cars, User user) {
		
		for (Car car : cars) {
			
			System.out.println(car + "\n");
		}
		
		System.out.println("Please input VIN number of the car");
		int vin = scan.nextInt();
		System.out.println("Please enter your offer");
		int offer = scan.nextInt();
		
		Car offerCar = carDAO.selectCarByVIN(vin);
		
		Offer currentOffer = new Offer(vin, user.getUserName(), offer);
		
		if (offerCar.getVin() > 0) {
			carDAO.addOffer(currentOffer);
			actionsCustomer(scan, users, cars, user);
		} else {
			System.out.println("Incorrect VIN number");
			makeOffer(scan, users, cars, user);
		}
		
//		for (Car car : cars) {
//			
//			if (car.getVin() == vin) {
//				car.addOffers(user.getUserName(), offer);
//				carDAO.addCar(car);
//				actionsCustomer(scan, users, cars, user);
//			} 
//		}

		
	}

	public static void actionsEmployee (Scanner scan, Users users, List<Car> cars, User user) {
		
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
			int year = scan.nextInt();
			System.out.println("What is the price?");
			double price = scan.nextDouble();
			System.out.println("What is the VIN?");
			int VIN = scan.nextInt();
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
			int vin = scan.nextInt();
			carDAO.removeCar(vin);
//			for (Car car : cars) {
//				
//				if (car.getVin() == vin) {
//					carDAO.removeCar(car);
//				}
//				
//			}
			actionsEmployee(scan, users, cars, user);
			break;
		case "vp":
			
			for (Car car : cars) {
				
				if (!car.getBelongsTo().equalsIgnoreCase("dealership")) {
					
			double payment = car.getPrice()/60;
			System.out.println("Owner: " + car.getBelongsTo() + " Make: " + car.getMake() + " Model: " + car.getModel() + " Remaining Paymets: 60 months" + " Monthly Payment: $" + df2.format(payment)  + "\n");
			actionsEmployee(scan, users, cars, user);
			} 			 
			}
			actionsEmployee(scan, users, cars, user);
			
			break;
		case "exit":
			initialScreen(scan);
			break;
		default:
			System.out.println("Try again");
			actionsEmployee(scan, users, cars, user);
		}
		
	}

	private static void reviewOffer(Scanner scan, Users users, List<Car> cars, User user) {
		
//		int amount;
//		String cust;
		
		
		List <Offer> offers = new ArrayList<Offer>();
		offers = carDAO.getOffers();
		
		for (Offer offer : offers) {
			
			System.out.println("OfferID: " + offer.getOfferId() + " VIN: " + offer.getCarVin() + " Offer: $" + offer.getOffer()  + " Customer: "+ offer.getCustomer() + "\n");
			
		}
		
//		for (Car car : cars) {
//			
//			if (car.getOffers() != null) {
//				
//				HashMap<String, Integer> offers = car.getOffers();
//				Iterator it = offers.entrySet().iterator();
//				
//				while(it.hasNext()) {
//					Map.Entry offer = (Map.Entry)it.next();
//					amount = (int) offer.getValue();
//					cust = (String) offer.getKey();
//					System.out.println("VIN: " + car.getVin() + " Offer: $" + amount  + " Customer: "+ cust + "\n");
//				}
//				
//			}
//			
//		}
		
		System.out.println("Do you want to accept an offer? (y or n)");
		String res = scan.next();
		
		if (res.equalsIgnoreCase("y")) {
			System.out.println("Please enter Offer ID");
			int offerId = scan.nextInt();
				handlePurchase(scan, users, cars, user, offerId);
			
		} else {
			actionsEmployee(scan, users, cars, user);
		}
	}

	private static void handlePurchase(Scanner scan, Users users, List<Car> cars, User user, int offerId) {
		
		
		Offer offer = carDAO.getOffer(offerId);
			
		
		carDAO.changeCarOwnership(offer.getCarVin(), offer.getCustomer(), offer.getOffer());
		
		carDAO.softDeleteOtherOffers(offer.getCarVin());
		
		//TODO do some logging here
		
		
//		for (Car car : cars) {
//			
//			if(car.getVin() == vin) {
//				
//				car.setBelongsTo(userName);
//				
//				HashMap<String, Integer> offers = car.getOffers();
//				Integer offer = offers.get(userName);
//				int payment = offer/60;
//				
//				car.setPayments(60, payment);			
//				car.removeOffers();
//				carDAO.addCar(car);
//				
//				
//			}
//				
//			
//		}
		
		actionsEmployee(scan, users, cars, user);
		
	}
	
	
		
	
}
