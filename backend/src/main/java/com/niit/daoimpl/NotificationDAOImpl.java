package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.NotificationDAO;
import com.niit.model.Notification;

@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;
		
		public void addNotification(Notification notification) {
			  Session session=sessionFactory.getCurrentSession();
			  session.save(notification);
			
		}

		public List<Notification> getAllNotification(String email) {
			Session session=sessionFactory.getCurrentSession();
			//select * from notification where viewed=0 and userToBeNotified_email=? - SQL
			Query query=session.createQuery("from Notification where viewed=0 and userToBeNotified.email=?");
			query.setString(0, email);

			List<Notification> notifications=query.list();
			return notifications;
		}

		public Notification getNotification(int notificationId) {
			Session session=sessionFactory.getCurrentSession();
			Notification notification=(Notification)session.get(Notification.class, notificationId);
			return notification;
		}

		public void updateNotification(int notificationId) {
			Session session=sessionFactory.getCurrentSession();
			Notification notification=(Notification)session.get(Notification.class, notificationId);
			notification.setViewed(true);
			session.update(notification);//update notification set viewed=1 where id=?
		}

}
