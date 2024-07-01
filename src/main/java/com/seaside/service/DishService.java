package com.seaside.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.seaside.entity.Dish;
import com.seaside.exception.InvalidDishIdException;
import com.seaside.repository.DishRepository;

public class DishService {
	DishRepository dishRepository;

	public DishRepository getDishRepository() {
		return dishRepository;
	}

	@Autowired
	public void setDishRepository(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public DishService(DishRepository dishRepository) {
		super();
		this.dishRepository = dishRepository;
	}

	public DishService() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Save a new dish
	public Dish saveDish(Dish dish) {
		return dishRepository.save(dish);
	}

	// Find all dishes
	public List<Dish> findAllDishes() {
		return dishRepository.findAll();
	}

	// Find dish by id
	public Dish findDishById(int id) throws InvalidDishIdException {
		Optional<Dish> optDish = dishRepository.findById(id);
		if (!(optDish.isPresent())) {
			throw new InvalidDishIdException("There is no dish with id " + id);
		}
		return optDish.get();
	}

	// Edit dish
	public Dish editDish(Dish dish) throws InvalidDishIdException {
		findDishById(dish.getId());
		return dishRepository.save(dish);
	}

	// Delete Dish by id
	public Dish deleteDish(int id) throws InvalidDishIdException {
		Dish dish = findDishById(id);
		dishRepository.delete(dish);
		return dish;
	}
}
