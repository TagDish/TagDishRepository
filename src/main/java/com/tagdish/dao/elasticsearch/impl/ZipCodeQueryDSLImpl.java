package com.tagdish.dao.elasticsearch.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tagdish.constant.TagDishDomainConstant;
import com.tagdish.dao.elasticsearch.ZipCodeQueryDSL;
import com.tagdish.domain.elasticsearch.ZipCode;
import com.tagdish.domain.location.Location;

@Repository
public class ZipCodeQueryDSLImpl implements ZipCodeQueryDSL {

	@Autowired
	private Client client;

	public ZipCode getZipCode(long zipCode) {

		SearchResponse response = client.prepareSearch(TagDishDomainConstant.TAGDISH_INDEX_NAME)
				.setTypes(TagDishDomainConstant.ZIPCODE_TYPE)
				.setQuery(QueryBuilders.matchQuery("zipCode", zipCode))
				.execute().actionGet();

		ZipCode zc = null;
		Location location = null;
		if(response != null && response.getHits() != null) {

			zc = new ZipCode();
			location = new Location();
			zc.setLocation(location);
			for (SearchHit searchHit : response.getHits()) {
				if (response.getHits().getHits().length <= 0) {
					System.out.println("Nothing found");
				}

				zc.setZipClass((String) searchHit.getSource().get("zipClass"));
				zc.setZipCode(new Long((Integer) searchHit.getSource().get("zipCode")));
				zc.setCity((String) searchHit.getSource().get("city"));
				zc.setCounty((String) searchHit.getSource().get("county"));
				
				location.setLatitude((String) searchHit.getSource().get("latitude"));
				location.setLongitude((String) searchHit.getSource().get("longitude"));
			}
			
		}

		return zc;
	}
}
