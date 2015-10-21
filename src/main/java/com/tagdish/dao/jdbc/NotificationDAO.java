package com.tagdish.dao.jdbc;

import java.util.List;

import com.tagdish.domain.db.NotificationDB;

public interface NotificationDAO {

	public abstract void createNotification(NotificationDB notificationDB);

	public abstract List<NotificationDB> getNotificationList(NotificationDB notificationDB);
	
	public abstract void updateNotification(NotificationDB notificationDB);
	
	public abstract void createSearchResultLog(Long notificationId, String searchResult);
}