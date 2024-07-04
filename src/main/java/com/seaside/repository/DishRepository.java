package com.seaside.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaside.entity.Customer;
import com.seaside.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
	public List<Dish> findAllDishesByCustomer(Customer customer);

}
