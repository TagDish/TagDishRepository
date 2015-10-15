package com.tagdish.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tagdish.domain.db.NotificationDB;

public class NotificationRowMapper implements RowMapper<NotificationDB>{

	public NotificationDB mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int index = 1;
		NotificationDB notificationDB = new NotificationDB();
		notificationDB.setNotificationId(rs.getLong(index++));
		notificationDB.setTrasactionId(rs.getString(index++));
		notificationDB.setUserId(rs.getLong(index++));
		notificationDB.setAction(rs.getString(index++));
		
		notificationDB.setCount(rs.getInt(index++));
		notificationDB.setTimestamp(rs.getLong(index++));
		notificationDB.setData(rs.getString(index++));
		
		return notificationDB;		
	}
}
