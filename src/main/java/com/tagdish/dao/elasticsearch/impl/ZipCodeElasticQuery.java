package com.tagdish.dao.elasticsearch.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipCodeElasticQuery {

	@Autowired
	private Client client;

	public SearchResponse getZipCode(long zipCode){
		
		SearchResponse response = client.prepareSearch("tag_dish").setTypes("zipcode")
                .setQuery(QueryBuilders.matchQuery("zipCode", zipCode))
                .execute()
                .actionGet();
		return response;
	}
}
