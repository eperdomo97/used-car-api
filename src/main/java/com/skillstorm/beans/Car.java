package com.skillstorm.beans;

import java.util.Objects;

public class Car {

	private int id;
	private String make;
	private String model;
	private int modelYear;
	private String color;
	private int mileage;
	private int price;
	private String title;
	
	public Car() {
		super();
	}

	public Car(String make, String model, int modelYear, String color, int mileage, int price, String title) {
		super();
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
		this.color = color;
		this.mileage = mileage;
		this.price = price;
		this.title = title;
	}

	public Car(int id, String make, String model, int modelYear, String color, int mileage, int price, String title) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
		this.color = color;
		this.mileage = mileage;
		this.price = price;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, id, make, mileage, model, modelYear, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(color, other.color) && id == other.id && Objects.equals(make, other.make)
				&& mileage == other.mileage && Objects.equals(model, other.model) && modelYear == other.modelYear
				&& price == other.price && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", modelYear=" + modelYear + ", color=" + color
				+ ", mileage=" + mileage + ", price=" + price + ", title=" + title + "]";
	}
	
}
