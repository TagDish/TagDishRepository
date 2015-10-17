package com.tagdish.dao.jdbc.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tagdish.dao.jdbc.DishDAO;
import com.tagdish.dao.jdbc.rowmapper.DishRowMapper;
import com.tagdish.domain.db.DishDB;
import com.tagdish.exception.DBException;

@Repository
public class DishDAOImpl implements DishDAO, InitializingBean {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}  
	         
    public List<DishDB> listDish() throws DBException {
        String sql = "SELECT * FROM Dish";
        List<DishDB> listContact = jdbcTemplate.query(sql, new DishRowMapper());
     
        return listContact;
    }

  
	
}
