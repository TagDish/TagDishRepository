package com.tagdish.dao.jdbc;

import java.util.List;

import com.tagdish.domain.db.DishDB;
import com.tagdish.exception.DBException;

public interface DishDAO {

	public List<DishDB> listDish() throws DBException;
}
