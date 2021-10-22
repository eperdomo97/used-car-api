package com.skillstorm.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.skillstorm.beans.Car;
import com.skillstorm.data.CarDAO;

public class CarDAOTest {

	@Test
	public void createTest() {
		CarDAO dao = new CarDAO();
		boolean result = false;
		//(String make, String model, int modelYear, String color, int mileage, int price, String title)
		Car car = new Car("Mazda", "MX-5", 1997, "Red", 78192, 7000, "Clean");
		
		Car car1 = new Car("Toyota", "MR-2", 1997, "Yellow", 92043, 9400, "Clean");
		
		car = dao.create(car);
		dao.create(car1);
		if(car.getId() != 0) {
			result = true;
		}
		assertTrue(result);
		
	}
	
	@Test
	public void findByAllTest() {
		CarDAO dao = new CarDAO();
		boolean result = false;
		
		List<Car> cars = dao.findAll();
		if(cars != null) {
			
			result = true;
			
			for(Car c : cars) {
				System.out.println(c.toString());
			}
		}
		
		assertTrue(result);
	}
	
}
