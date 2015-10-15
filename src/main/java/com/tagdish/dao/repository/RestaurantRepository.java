package com.tagdish.dao.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tagdish.domain.elasticsearch.Restaurant;

public interface RestaurantRepository extends ElasticsearchRepository < Restaurant, Long > {

	public List<Restaurant> findByRestaurantName(String name);
	
	public Restaurant findByRestaurantId(Long restaurantId);
}
