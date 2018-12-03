package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDAO;
import com.niit.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	

	public void registerUser(User user) {
     Session session=sessionFactory.getCurrentSession();
     session.save(user);

	}


	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		//select * from user_s190038 where email='john@niit.com'
		User user=(User)session.get(User.class, email);
		if(user==null)
			return true; //entered email is unique
		else
			return false;//entered email is duplicate,another user has already registered with same email id 
	}


	public User login(User user) {
		/*Session session=sessionFactory.getCurrentSession();*/
		
		return (User)sessionFactory.getCurrentSession().createQuery("from User where email='"+user.getEmail()  +"'and password= '" + user.getPassword()+ "'").uniqueResult();
/*		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();*///only one object
	}


	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}


	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}


	public List<User> findAllUsers() {
		
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}


	public User findUserByEmail(String email) {
		return (User)sessionFactory.getCurrentSession().createQuery("from User where email='"+email+"'").uniqueResult();
	}

}
