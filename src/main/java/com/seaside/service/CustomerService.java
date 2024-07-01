package com.seaside.service;

import java.util.List;
import java.util.Optional;

import com.seaside.entity.Customer;
import com.seaside.exception.InvalidCustomerIdException;
import com.seaside.repository.CustomerRepository;

public class CustomerService {
	CustomerRepository customerRepository;

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	// Save customer
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	// Find all customers
	public List<Customer> findAllCustomers(){
		return customerRepository.findAll();
	}
	
	// Find customer by id
	public Customer findCustomerById(int id) throws InvalidCustomerIdException {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if(!(optCustomer.isPresent())) {
			throw new InvalidCustomerIdException("Not Customer found with id "+id);
		}
		return optCustomer.get();
	}
	
	// Edit customer
	public Customer editCustomer(Customer customer) throws InvalidCustomerIdException {
		findCustomerById(customer.getId());
		return customerRepository.save(customer);
	}
	
	// Delete customer by id
	public Customer deleteCustomer(int id) throws InvalidCustomerIdException {
		Customer customer = findCustomerById(id);
		customerRepository.delete(customer);
		return customer;
	}
	
}
