package com.seaside.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seaside.entity.Customer;
import com.seaside.entity.Dish;
import com.seaside.exception.InvalidCustomerIdException;
import com.seaside.service.CustomerService;
import com.seaside.service.DishService;

@RequestMapping("/customer")
public class customerController {
	CustomerService customerService;
	DishService dishService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public void setDishService(DishService dishService) {
		this.dishService = dishService;
	}
	public customerController() {
		super();
		// TODO Auto-generated constructor stub
	}
	public customerController(CustomerService customerService, DishService dishService) {
		super();
		this.customerService = customerService;
		this.dishService = dishService;
	}
	
	// Find all Customers
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.findAllCustomers();
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return responseEntity;
	}
	
	// Find Customer by id
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) throws InvalidCustomerIdException{
		Customer customer = customerService.findCustomerById(id);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
		return responseEntity;
	}
	
	// Save new Customer
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		Customer savedCustomer = customerService.saveCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(savedCustomer, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	// Edit Customer
	@PutMapping("/{id}")
	public ResponseEntity<Customer> editCustomer(@PathVariable int id, @RequestBody Customer customer) throws InvalidCustomerIdException{
		if(id != customer.getId()) {
			throw new InvalidCustomerIdException("Customer not found with id : "+id);
		}
		Customer editedCustomer = customerService.editCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(editedCustomer, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	// Delete Customer
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) throws InvalidCustomerIdException{
		Customer deletedCustomer = customerService.deleteCustomer(id);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
		return responseEntity;
	}
	
	// Get all dishes of customer
	public ResponseEntity<List<Dish>> getAllDishesByCustomer(@PathVariable int id) throws InvalidCustomerIdException{
		Customer customer = customerService.findCustomerById(id);
		List<Dish> dishes = dishService.findAllDishesByCustomer(customer);
		ResponseEntity<List<Dish>> responseEntity = new ResponseEntity<List<Dish>>(dishes, HttpStatus.OK);
		return responseEntity;
	}
}
