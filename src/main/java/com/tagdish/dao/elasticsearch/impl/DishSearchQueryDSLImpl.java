package com.tagdish.dao.elasticsearch.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tagdish.constant.TagDishDomainConstant;
import com.tagdish.dao.elasticsearch.DishSearchQueryDSL;
import com.tagdish.domain.elasticsearch.DishSearch;
import com.tagdish.domain.location.Address;
import com.tagdish.domain.location.Location;

@Repository
public class DishSearchQueryDSLImpl implements DishSearchQueryDSL {

	@Autowired
	private Client client;

	public List<DishSearch> searchDish(String name, Collection<Long> zipCodeList) {

		LinkedList<DishSearch> dishSearchList = null;
		SearchResponse response = null;
		
		response = client.prepareSearch(TagDishDomainConstant.TAGDISH_INDEX_NAME)
				.setTypes(TagDishDomainConstant.DISH_SEARCH_TYPE)
				.setQuery(QueryBuilders.moreLikeThisQuery("dishName").likeText(name))
				.setPostFilter(FilterBuilders.inFilter("zipCode", zipCodeList))
				.execute().actionGet();
		
		dishSearchList = convertSearchResponseToDishSearchList(response);

		return dishSearchList;
	}
	
	public List<DishSearch> fuzzySearchDish(String name, Collection<Long> zipCodeList) {

		LinkedList<DishSearch> dishSearchList = null;
		SearchResponse response = null;
		
		response = client.prepareSearch(TagDishDomainConstant.TAGDISH_INDEX_NAME)
				.setTypes(TagDishDomainConstant.DISH_SEARCH_TYPE)
				.setQuery(QueryBuilders.fuzzyLikeThisQuery("dishName").likeText(name))
				.setPostFilter(FilterBuilders.inFilter("zipCode", zipCodeList))
				.execute().actionGet();
		
		dishSearchList = convertSearchResponseToDishSearchList(response);

		return dishSearchList;
	}
	
	
	private LinkedList<DishSearch> convertSearchResponseToDishSearchList(SearchResponse response) {
		
		DishSearch dishSearch = null;
		Location location = null;
		Address address = null;
		LinkedList<DishSearch> dishSearchList = null;
		if(response != null && response.getHits() != null) {

			dishSearchList = new LinkedList<DishSearch>();
			for (SearchHit searchHit : response.getHits()) {
				if (response.getHits().getHits().length <= 0) {
					System.out.println("Nothing found");
				}

				dishSearch = new DishSearch();
				location = new Location();
				address = new Address();
				dishSearch.setLocation(location);
				dishSearch.setAddress(address);
				
				dishSearch.setDishId(new Long((Integer) searchHit.getSource().get("dishId")));
				dishSearch.setDishName((String) searchHit.getSource().get("dishName"));
				dishSearch.setImageUrl((String) searchHit.getSource().get("imageUrl"));
				dishSearch.setPrice((Float) searchHit.getSource().get("price"));
				
				dishSearch.setRestaurantId(new Long((Integer) searchHit.getSource().get("restaurantId")));
				dishSearch.setRestaurantType((String) searchHit.getSource().get("restaurantType"));
				
				location.setLatitude((String) searchHit.getSource().get("latitude"));
				location.setLongitude((String) searchHit.getSource().get("longitude"));
				
				dishSearchList.add(dishSearch);
			}
			
		}
		return dishSearchList;
	}
}
