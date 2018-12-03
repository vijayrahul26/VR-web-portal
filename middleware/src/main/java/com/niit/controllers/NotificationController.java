package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.NotificationDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.Notification;

@Controller
public class NotificationController 
{
	@Autowired
	private NotificationDAO notificationDao;
	@RequestMapping(value="/getallnotifications",method=RequestMethod.GET)
	public ResponseEntity<?> getAllNotifications(HttpSession session){//get email id of the loggedin user
		//CHECK FOR AUTHENTICATION
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<Notification> notifications=notificationDao.getAllNotification(email);
			return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	@RequestMapping(value="/getnotification/{notificationId}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int notificationId,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		Notification notification=notificationDao.getNotification(notificationId);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	@RequestMapping(value="/updatenotification/{notificationId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotificationViewedStatus(@PathVariable int notificationId,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		notificationDao.updateNotification(notificationId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
