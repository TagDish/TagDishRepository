package com.tagdish.dao.elasticsearch;

import java.util.Collection;
import java.util.List;

import com.tagdish.domain.elasticsearch.DishSearch;

public interface DishSearchQueryDSL {

	public abstract List<DishSearch> searchDish(String name,
			Collection<Long> zipCodeList);

	public abstract List<DishSearch> fuzzySearchDish(String name,
			Collection<Long> zipCodeList);

}