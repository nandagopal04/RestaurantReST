package com.seaside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaside.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	

}
