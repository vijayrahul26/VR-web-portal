package com.niit.dao;

import java.util.List;

import com.niit.model.Notification;

public interface NotificationDAO 
{
	void addNotification(Notification notification);
	List<Notification>  getAllNotification(String email);
	Notification getNotification(int notificationId);
	void updateNotification(int notificationId);

}
