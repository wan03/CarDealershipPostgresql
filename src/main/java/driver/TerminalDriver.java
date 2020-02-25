package driver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.CarDealership.DAO.CarSerializationDAO;
import com.revature.CarDealership.DAO.UserSerializationDAO;
import com.revature.CarDealership.pojos.Car;
import com.revature.CarDealership.pojos.Customer;
import com.revature.CarDealership.pojos.Offer;
import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;

public class TerminalDriver {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	private static final Logger Log = Logger.getLogger("TerminalDriver");
	private static CarSerializationDAO carDAO = new CarSerializationDAO();
	private static UserSerializationDAO userDAO = new UserSerializationDAO();
		
	public static void main(String args[]){
		Log.info("System Started");
        Scanner start = new Scanner(System.in);  
        System.out.println("Hello, thank you for visiting our dealer.");            
             
        initialScreen(start);
      
        start.close();           
        } 
	
	public static void initialScreen(Scanner scan) {

		List<Car> cars = carDAO.readAllCars();
        Users users = userDAO.readAllUsers();
		
        
        determineAccountStatus(scan, users, cars);
		
		
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
				Log.info(user.getUserName() + " has logged in.");
				actionsCustomer(scan, users, cars, user);
				
			} else if (user.getUserType().equalsIgnoreCase("employee")) {
				Log.info(user.getUserName() + " has logged in.");
				actionsEmployee(scan, users, cars, user);
				
			}
		} else {
			System.out.println("Sorry, wrong username or password");
			loginProcedure(scan, users, cars);
		}

				
	}

	public static void createUser (Scanner scan, Users users, List<Car> cars) {
		
		System.out.println("Answer the following questions to create a new user");
		System.out.println("What is your username?");
		String username = scan.next();
		System.out.println("What is your password?");
		String password = scan.next();
		
			
			Customer newCustomer = new Customer(username, password); 
			userDAO.addUser(newCustomer);
			Log.info(username + " has been created as a new user.");
			User user = userDAO.selectUser(username);
			actionsCustomer(scan, users, cars, user);
		
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

			break;
		case "vp":
			viewPayments(scan, users, cars, user);

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
		
		if (!offerCar.isEmpty()) {
			carDAO.addOffer(currentOffer);
			Log.info(user.getUserName() + " has made an offer of " + offer + "on vin: " + vin);
			actionsCustomer(scan, users, cars, user);
		} else {
			System.out.println("Incorrect VIN number");
			makeOffer(scan, users, cars, user);
		}
		
	
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
			Car newCar = new Car(make, model, year, price);			
			carDAO.addCar(newCar);
			Log.info(make + " " + model + " has been added to the lot." );
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
			Log.info("VIN: " + vin + " has been removed from the lot." );

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
		
	
		
		List <Offer> offers = new ArrayList<Offer>();
		offers = carDAO.getOffers();
		
		for (Offer offer : offers) {
			
			System.out.println("OfferID: " + offer.getOfferId() + " VIN: " + offer.getCarVin() + " Offer: $" + offer.getOffer()  + " Customer: "+ offer.getCustomer() + "\n");
			
		}

		
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
		Log.info("Offer: " + offerId + " has been accepted by " + user.getUserName());
		
		carDAO.softDeleteOtherOffers(offer.getCarVin());
		
		//TODO do some logging here
		
		actionsEmployee(scan, users, cars, user);
		
	}
	
	
		
	
}
