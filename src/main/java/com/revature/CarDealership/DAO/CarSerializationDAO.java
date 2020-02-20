package com.revature.CarDealership.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.CarDealership.pojos.Automobiles;
import com.revature.CarDealership.pojos.Car;

public class CarSerializationDAO {
	
	public void addCar(Car c) {
		
		
		Automobiles cars = readAllCars();
		if(cars.contains(c)) {
			cars.remove(c);
			cars.add(c);		
		} else { cars.add(c);}
		String filename = "cars.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cars);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

public Automobiles readAllCars() {
	
	String filename;
	filename = "cars.dat";
	Automobiles b = null;
	try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
		b = (Automobiles) ois.readObject();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return b;
}
	
	// TODO This doesn't work yet, don't know how to remove a file.
	public void removeCar (Car c) {
		
		Automobiles cars = readAllCars();
		if (cars.contains(c)) {
			
			cars.remove(c);		
		}
		String filename = "cars.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cars);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
	public void changeCarOwnership (Car car, String userName) {
		
		Automobiles cars = readAllCars();
		cars.changeOwnership(car, userName);		
		String filename = "cars.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cars);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
