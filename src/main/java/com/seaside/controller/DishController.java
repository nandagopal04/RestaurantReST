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

import com.seaside.entity.Dish;
import com.seaside.exception.InvalidDishIdException;
import com.seaside.service.DishService;

@RequestMapping("/dish")
public class DishController {
	DishService dishService;

	public void setDishService(DishService dishService) {
		this.dishService = dishService;
	}
	
	// Save a new dish
	@PostMapping
	public ResponseEntity<Dish> saveDish(@RequestBody Dish dish){
		Dish savedDish = dishService.saveDish(dish);
		ResponseEntity<Dish> responseEntity = new ResponseEntity<Dish>(savedDish, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	// Find dish by id
	@GetMapping("/{id}")
	public ResponseEntity<Dish> findDishById(@PathVariable int id) throws InvalidDishIdException{
		Dish dish = dishService.findDishById(id);
		ResponseEntity<Dish> resposneEntity = new ResponseEntity<Dish>(dish, HttpStatus.FOUND);
		return resposneEntity;
	}
	
	// Find all dishes
	@GetMapping
	public ResponseEntity<List<Dish>> findAllDishes(){
		List<Dish> dishes = dishService.findAllDishes();
		ResponseEntity<List<Dish>> responseEntity = new ResponseEntity<List<Dish>>(dishes, HttpStatus.FOUND);
		return responseEntity;
	}
	
	// Edit Dish
	@PutMapping("/{id}")
	public ResponseEntity<Dish> editDish(@PathVariable int id, @RequestBody Dish dish) throws InvalidDishIdException{
		if(id != dish.getId()) {
			throw new InvalidDishIdException("No dish found with id : "+id);
		}
		Dish editedDish = dishService.editDish(dish);
		ResponseEntity<Dish> resposneEntity = new ResponseEntity<Dish>(editedDish, HttpStatus.OK);
		return resposneEntity;
	}
	
	// Delete dish
	@DeleteMapping("/{id}")
	public ResponseEntity<Dish> deleteDish(@PathVariable int id) throws InvalidDishIdException{
		Dish dish = dishService.findDishById(id);
		dishService.deleteDish(id);
		ResponseEntity<Dish> reponseEntity = new ResponseEntity<Dish>(dish, HttpStatus.OK);
		return reponseEntity;
	}
}
