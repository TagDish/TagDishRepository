package com.tagdish.dao.jdbc.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tagdish.dao.jdbc.NotificationDAO;
import com.tagdish.dao.jdbc.preparedstatementsetter.NotificationPreparedStatementSetter;
import com.tagdish.dao.jdbc.rowmapper.NotificationRowMapper;
import com.tagdish.domain.db.NotificationDB;

public class NotificationDAOImpl implements NotificationDAO {

	private JdbcTemplate jdbcTemplate;
	 
    public NotificationDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }	
    
    public void saveNotification(NotificationDB notificationDB) {

        // insert
        String sql = "INSERT INTO notification (trasactionId, action, userId, "
        			+ "timestamp, count, data)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, notificationDB.getTrasactionId(), notificationDB.getAction(), 
        		notificationDB.getUserId(), notificationDB.getTimestamp(), notificationDB.getCount(),
        		notificationDB.getData());     
    }    
    
    public void updateNotification(NotificationDB notificationDB) {

        // update
        String sql = "UPDATE notification SET trasactionId = ?, action = ?, accountId = ?"
        			+ "timestamp= ?, count = ?, data =?) WHERE notificationId = ?";
        jdbcTemplate.update(sql, notificationDB.getTrasactionId(), notificationDB.getAction(), 
        		notificationDB.getUserId(), notificationDB.getTimestamp(), notificationDB.getCount(),
        		notificationDB.getData(), notificationDB.getNotificationId());     
    }  
    
    public List<NotificationDB> getNotificationList(NotificationDB notificationDB) {

        // get
    	List<NotificationDB> notificationDBList = null;
        String sql = "SELECT * notification WHERE trasactionId = ? and action = ? and data = ?";
        notificationDBList = jdbcTemplate.query(sql, new NotificationPreparedStatementSetter(notificationDB), 
        		new NotificationRowMapper());  
        return notificationDBList;
    }     
}
