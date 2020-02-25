package com.revature.CarDealership.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.CarDealership.pojos.User;
import com.revature.CarDealership.pojos.Users;
import com.revature.CarDealership.util.ConnectionFactory;

public class UserSerializationDAO {
	
	

	public void addUser (User u) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
		
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?) ON CONFLICT (username) DO NOTHING");
		ps.setString(1, u.getUserName());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getUserType());
		
		
		ps.execute();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
		
		
		
		
//		String filename = "users.dat";
//		Users users = readAllUsers();
//		
//		if (!users.contains(u)) {
//			
//			users.add(u);
//		}
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(users);
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
	
	
	public User selectUser(String username) {
		// TODO Auto-generated method stub
		User user = null;
	try (Connection conn = ConnectionFactory.getConnection()){
			
			
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
		
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}	
	
	public Users readAllUsers (){
		
		
		Users users = new Users();
		try (Connection conn = ConnectionFactory.getConnection()){
				
				
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return users;
		
		
		
		
		
		
//		String filename;
//		filename = "users.dat";
//		Users b = null;
//		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
//			b = (Users) ois.readObject();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return b;	
		
	}
	
	public void removeUser (User u) {
		
	try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE username=?");
			ps.setString(1, u.getUserName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
		
		
		
		
		
		
		
//		String filename;
//		filename = "users.dat";
//		Users users = readAllUsers();
//		if (users.contains(u)) {
//			
//			users.remove(u);
//		}
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(users);
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
	
	

}
