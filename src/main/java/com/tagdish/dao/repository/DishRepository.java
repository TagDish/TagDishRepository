package com.tagdish.dao.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tagdish.domain.elasticsearch.Dish;

public interface DishRepository extends ElasticsearchRepository < Dish, Long > {

	public List<Dish> findByDishName(String name);
	
	public Dish findByDishId(Long dishId);
	
	public LinkedList<Dish> findByDishNameContainingAndZipCodeIn(String name, Collection<Long> zipCode);

	@Query("{\"match\" : {\"dishId\" : ?0}}")
	public Dish getDishById(Long dishId);

	@Query("{\"match\" : {\"name\" : ?0}}")
	public LinkedList<Dish> getDishByName(String name);	

}
