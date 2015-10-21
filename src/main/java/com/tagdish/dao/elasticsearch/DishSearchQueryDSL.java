package com.tagdish.dao.elasticsearch;

import java.util.List;

import com.tagdish.domain.elasticsearch.DishSearch;

public interface DishSearchQueryDSL {

	public abstract List<DishSearch> searchDish(String name,
			long[] zipCodeArray);

	public abstract List<DishSearch> fuzzySearchDish(String name,
			long[] zipCodeArray);

}