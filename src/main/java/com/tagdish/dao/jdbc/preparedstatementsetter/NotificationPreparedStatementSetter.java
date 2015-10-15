package com.tagdish.dao.jdbc.preparedstatementsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tagdish.domain.db.NotificationDB;

public class NotificationPreparedStatementSetter implements PreparedStatementSetter{

	private NotificationDB notificationDB;
	
	
	public NotificationPreparedStatementSetter(NotificationDB notificationDB) {
		super();
		this.notificationDB = notificationDB;
	}


	public void setValues(PreparedStatement arg0) throws SQLException {
		
		int index = 1;
		
		arg0.setString(index++, notificationDB.getTrasactionId());
		arg0.setString(index++, notificationDB.getAction());
		arg0.setString(index++, notificationDB.getData());
	}
}
