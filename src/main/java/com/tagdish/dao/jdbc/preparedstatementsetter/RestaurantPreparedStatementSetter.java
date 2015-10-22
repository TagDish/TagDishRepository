package com.tagdish.dao.jdbc.preparedstatementsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class RestaurantPreparedStatementSetter implements PreparedStatementSetter{

	public void setValues(PreparedStatement arg0) throws SQLException {
		
		int index = 1;
		
		arg0.setInt(index++, 0);
		
	}
}
