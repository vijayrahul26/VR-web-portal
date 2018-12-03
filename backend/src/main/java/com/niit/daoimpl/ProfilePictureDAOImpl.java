package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProfilePictureDAO;
import com.niit.model.ProfilePicture;

@Repository("profilepictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO {
	@Autowired
private SessionFactory sessionFactory;
	public ProfilePicture saveOrUpdateProfilePic(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);//insert or update
		return profilePicture;
	}

	public ProfilePicture getImage(String email) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture) session.get(ProfilePicture.class, email);
		return profilePicture;
	}

}