package com.tagdish.dao.jdbc.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tagdish.dao.jdbc.NotificationDAO;
import com.tagdish.dao.jdbc.preparedstatementsetter.NotificationPreparedStatementSetter;
import com.tagdish.dao.jdbc.rowmapper.NotificationRowMapper;
import com.tagdish.domain.db.NotificationDB;

@Repository
public class NotificationDAOImpl implements NotificationDAO, InitializingBean {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	} 
    
    public void createNotification(NotificationDB notificationDB) {

        // insert
        String sql = "INSERT INTO notification (trasactionId, action, userId, "
        			+ "timestamp, count, data)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, notificationDB.getTrasactionId(), notificationDB.getAction(), 
        		notificationDB.getUserId(), new Timestamp(notificationDB.getTimestamp()), notificationDB.getCount(),
        		notificationDB.getData());     
    }    
    
    public void updateNotification(NotificationDB notificationDB) {

        // update
        String sql = "UPDATE notification SET trasactionId = ?, action = ?, userId = ?, "
        			+ "timestamp= ?, count = ?, data =? WHERE notificationId = ?";
        jdbcTemplate.update(sql, notificationDB.getTrasactionId(), notificationDB.getAction(), 
        		notificationDB.getUserId(), new Timestamp(notificationDB.getTimestamp()), notificationDB.getCount(),
        		notificationDB.getData(), notificationDB.getNotificationId());     
    }  
    
    public List<NotificationDB> getNotificationList(NotificationDB notificationDB) {

        // get
    	List<NotificationDB> notificationDBList = null;
        String sql = "SELECT * FROM notification WHERE trasactionId = ? and action = ? and data = ?";
        notificationDBList = jdbcTemplate.query(sql, new NotificationPreparedStatementSetter(notificationDB), 
        		new NotificationRowMapper());  
        return notificationDBList;
    }     
}
