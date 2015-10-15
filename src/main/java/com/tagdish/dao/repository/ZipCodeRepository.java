package com.tagdish.dao.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tagdish.domain.elasticsearch.ZipCode;

public interface ZipCodeRepository extends ElasticsearchRepository < ZipCode, Long > {

	public List<ZipCode> findByCityAndState(String city, String state);
	
	public List<ZipCode> findByCityLikeIgnoreCase(String city);
}
