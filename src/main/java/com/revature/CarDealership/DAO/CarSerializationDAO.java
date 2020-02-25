package com.revature.CarDealership.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.CarDealership.pojos.Car;
import com.revature.CarDealership.pojos.Offer;
import com.revature.CarDealership.util.ConnectionFactory;

public class CarSerializationDAO {
	
	
	public void addCar(Car c) {
		
	try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO cars VALUES(?,?,?,?,?,?) ON CONFLICT (vin) DO NOTHING");
			ps.setString(1, c.getMake());
			ps.setString(2, c.getModel());
			ps.setInt(3, c.getYear());
			ps.setDouble(4, c.getPrice());
			ps.setInt(5, (int) (Math.random() * 10000));
			ps.setString(6, "dealership");

			
			
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
//		Automobiles cars = readAllCars();
//		if(cars.contains(c)) {
//			cars.remove(c);
//			cars.add(c);		
//		} else { cars.add(c);}
//		String filename = "cars.dat";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(cars);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fos.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	}
	

	public Car selectCarByVIN(int vin) {
		
		Car car = null;
	try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM cars WHERE vin=?");
			ps.setInt(1, vin);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				car = new Car(rs.getString("make"), rs.getString("model"), rs.getInt("year"), rs.getDouble("price"), rs.getInt("vin"), rs.getString("owner"));
			
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return car;
	}	
	
public List<Car> readAllCars() {
	
//	Automobiles cars = new Automobiles();
	List <Car> cars = new ArrayList<Car>();
	try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM cars WHERE owner=?");
			ps.setString(1, "dealership");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cars.add(new Car(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cars;
	
//	String filename;
//	filename = "cars.dat";
//	Automobiles b = null;
//	try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
//		b = (Automobiles) ois.readObject();
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	} catch (ClassNotFoundException e) {
//		e.printStackTrace();
//	}
//	return b;
}

	public List<Car> getOwnedCars(String userName) {
		
		List <Car> cars = new ArrayList<Car>();
		try (Connection conn = ConnectionFactory.getConnection()){
				
				
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM cars WHERE owner=?");
				ps.setString(1, userName);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					cars.add(new Car(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return cars;
		
	}
	
	
	public void removeCar (Car c) {
		
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM cars WHERE vin=?");
			ps.setDouble(1, c.getVin());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
//		Automobiles cars = readAllCars();
//		if (cars.contains(c)) {
//			
//			cars.remove(c);		
//		}
//		String filename = "cars.dat";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(cars);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fos.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
		
	}
	
	public void removeCar (int vin) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM cars WHERE vin=?");
			ps.setDouble(1, vin);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}

	
	public void changeCarOwnership (int vin, String userName, int price) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE cars SET owner=?, price=? WHERE vin=?");
			ps.setString(1, userName);
			ps.setDouble(2, price);
			ps.setInt(3, vin);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		
//		Automobiles cars = readAllCars();
//		cars.changeOwnership(car, userName);		
//		String filename = "cars.dat";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(cars);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fos.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	}
	
	public void addOffer (Offer o) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			
		PreparedStatement ps = conn.prepareStatement("INSERT INTO offers VALUES(?,?,?) ON CONFLICT (offerid) DO NOTHING");
		ps.setInt(1, o.getCarVin());
		ps.setString(2, o.getCustomer());
		ps.setInt(3, o.getOffer());
		
		
		ps.execute();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
	}
	
	public List<Offer> getOffers() {
		
		List <Offer> offers = new ArrayList<Offer>();
		try (Connection conn = ConnectionFactory.getConnection()){
				
				
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM offers WHERE status=?");
				ps.setBoolean(1, true);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					offers.add(new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return offers;
		
	}
	
	public Offer getOffer(int offerId) {
		
		Offer offer = null;
		try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM offers WHERE offerid=?");
			ps.setInt(1, offerId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				offer = new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return offer;
		
	}
	
	public void softDeleteOtherOffers(int vin) {
		
	
		try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("UPDATE offers SET status=? WHERE car=?");
			ps.setBoolean(1, false);
			ps.setInt(2, vin);
			ps.execute();
			
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	

}
