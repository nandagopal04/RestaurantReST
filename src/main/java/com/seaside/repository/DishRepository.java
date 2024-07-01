package com.seaside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seaside.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {

}
