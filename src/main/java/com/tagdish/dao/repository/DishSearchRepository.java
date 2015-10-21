package com.tagdish.dao.repository;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tagdish.domain.elasticsearch.DishSearch;

public interface DishSearchRepository extends ElasticsearchRepository < DishSearch, Long > {
	
	public DishSearch findByDishId(Long dishId);
	
	public LinkedList<DishSearch> findByDishNameContainingAndZipCodeIn(String name, Collection<String> zipCode);
}
