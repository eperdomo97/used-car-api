package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.beans.Car;

public class CarDAO {

	// database credentials
	private static final String url = "jdbc:mysql://localhost:3306/used-cars";
	private static final String username = "root";
	private static final String password = "root";
	
	//1. load class into memory
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Car create(Car car) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)){
			
			// disable autocommit
			conn.setAutoCommit(false);
			String sql = "INSERT INTO cars(make, model, modelYear, color, mileage, price, title) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, car.getMake());
				stmt.setString(2, car.getModel());
				stmt.setInt(3, car.getModelYear());
				stmt.setString(4, car.getColor());
				stmt.setInt(5, car.getMileage());
				stmt.setInt(6, car.getPrice());
				stmt.setString(7, car.getTitle());
				
				stmt.executeUpdate();
				
				
				// getting back the auto-incremented id from database
				ResultSet keys = stmt.getGeneratedKeys();
				while(keys.next()) {
					car.setId(keys.getInt(1));
				}
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
		return car;
	}
	
	public List<Car> findAll() {
		List<Car> cars = new LinkedList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM cars";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Car car = new Car();
					car.setId(rs.getInt("id"));
					car.setMake(rs.getString("make"));
					car.setModel(rs.getString("model"));
					car.setModelYear(rs.getInt("modelYear"));
					car.setColor(rs.getString("color"));
					car.setMileage(rs.getInt("mileage"));
					car.setPrice(rs.getInt("price"));
					car.setTitle(rs.getString("title"));
					
					cars.add(car);
				}
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
		return cars;
	}
	
	public List<Car> findByMake(String make) {
		List<Car> cars = new LinkedList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM cars WHERE make = ?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				stmt.setString(1, make);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Car car = new Car();
					car.setMake(rs.getString("make"));
					car.setModel(rs.getString("model"));
					car.setModelYear(rs.getInt("modelYear"));
					car.setColor(rs.getString("color"));
					car.setMileage(rs.getInt("mileage"));
					car.setPrice(rs.getInt("price"));
					car.setTitle(rs.getString("title"));
					
					cars.add(car);
				}
				
				conn.commit();
				System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		return cars;
	}
	
	public List<Car> findByModel(String make, String model) {
		List<Car> cars = new LinkedList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM cars WHERE make = ? AND model = ?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				stmt.setString(1, make);
				stmt.setString(2, model);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Car car = new Car();
					car.setMake(rs.getString("make"));
					car.setModel(rs.getString("model"));
					car.setModelYear(rs.getInt("modelYear"));
					car.setColor(rs.getString("color"));
					car.setMileage(rs.getInt("mileage"));
					car.setPrice(rs.getInt("price"));
					car.setTitle(rs.getString("title"));
					
					cars.add(car);
				}
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
		return cars;
	}
	
	public List<Car> findById(int id) {
		List<Car> cars = new LinkedList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM cars WHERE id = ?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Car car = new Car();
					car.setMake(rs.getString("make"));
					car.setModel(rs.getString("model"));
					car.setModelYear(rs.getInt("modelYear"));
					car.setColor(rs.getString("color"));
					car.setMileage(rs.getInt("mileage"));
					car.setPrice(rs.getInt("price"));
					car.setTitle(rs.getString("title"));
					
					cars.add(car);
				}
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
		return cars;
	}
	
	public void update(Car car) {
			
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "UPDATE cars SET make = ?, model = ?, modelYear = ?, color = ?, mileage = ?, price = ?, title = ? WHERE id = ?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				stmt.setString(1, car.getMake());
				stmt.setString(2, car.getModel());
				stmt.setInt(3, car.getModelYear());
				stmt.setString(4, car.getColor());
				stmt.setInt(5, car.getMileage());
				stmt.setInt(6, car.getPrice());
				stmt.setString(7, car.getTitle());
				stmt.setInt(8, car.getId());
				
				stmt.executeUpdate();
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
	}
	
	public void delete(Car car) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			//disable autocommit
			conn.setAutoCommit(false);
			String sql = "DELETE FROM cars WHERE id = ?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				stmt.setInt(1, car.getId());
				stmt.executeUpdate();
				
				conn.commit();
				//System.out.println("Transaction committed successfully");
				
			} catch(SQLException e) {
				e.printStackTrace();
				if(conn != null) {
					conn.rollback();
					//System.out.println("Transaction was rolled back");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed");
		}
	}
	
}
