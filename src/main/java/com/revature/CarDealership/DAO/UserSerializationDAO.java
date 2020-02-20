package com.revature.CarDealership.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;

public class UserSerializationDAO {
	

	public void addUser (User u) {
		
		String filename = "users.dat";
		Users users = readAllUsers();
		
		if (!users.contains(u)) {
			
			users.add(u);
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
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
	
	public Users readAllUsers (){
		
		String filename;
		filename = "users.dat";
		Users b = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			b = (Users) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;	
		
	}
	
	public void removeUser (User u) {
		
		String filename;
		filename = "users.dat";
		Users users = readAllUsers();
		if (users.contains(u)) {
			
			users.remove(u);
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
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
