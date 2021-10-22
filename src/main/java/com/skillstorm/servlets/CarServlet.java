package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Car;
import com.skillstorm.data.CarDAO;

@WebServlet(urlPatterns = "/api/cars")
public class CarServlet extends HttpServlet{

	CarDAO dao = new CarDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//use the DAO to retrieve all movies
		List<Car> allCars = dao.findAll();
		
		// convert list of movies to a json string
		String json = new ObjectMapper().writeValueAsString(allCars);
		
		//write the json string to our http response
		resp.getWriter().print(json);
		
		// setting content type to JSON
		resp.setContentType("application/json");
	}
	
	// NOT safe, NOT idempotent
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		// convert the request body into a java object
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		
		// updating the movie object to contain the generated id
		Car updatedCar = dao.create(car);
		
		// returning back the updated movie
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updatedCar));
		
		//set the status code to 201: CREATED
		resp.setStatus(201);
		
		// setting content type to JSON
		resp.setContentType("application/json");
	}
	
	//NOT safe, idempotent
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		// convert the request body into a java object
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		
		// update the database
		dao.update(car);
		
		// return the update
		resp.getWriter().print(new ObjectMapper().writeValueAsString(car));
		
		// set content type to json
		resp.setContentType("application/json");
	}
	
	//NOT safe, indempotent
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		// convert the request body into a java object
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		
		// delete from database
		dao.delete(car);
	}
}
