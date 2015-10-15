package com.tagdish.dao.jdbc.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tagdish.dao.jdbc.DishDAO;
import com.tagdish.dao.jdbc.rowmapper.DishRowMapper;
import com.tagdish.domain.db.DishDB;
import com.tagdish.exception.DBException;

public class DishDAOImpl implements DishDAO {

	private JdbcTemplate jdbcTemplate;
	 
    public DishDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
        
    public List<DishDB> listDish() throws DBException {
        String sql = "SELECT * FROM Dish";
        List<DishDB> listContact = jdbcTemplate.query(sql, new DishRowMapper());
     
        return listContact;
    }    
	
}
