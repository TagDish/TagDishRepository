package com.tagdish.dao.elasticsearch;

import com.tagdish.domain.elasticsearch.ZipCode;

public interface ZipCodeQueryDSL {

	public abstract ZipCode getZipCode(long zipCode);

}