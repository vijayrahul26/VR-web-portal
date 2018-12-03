package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogCommentDAO;
import com.niit.model.BlogComment;
@Repository
@Transactional

public class BlogCommentDAOImpl implements BlogCommentDAO {
	@Autowired
private SessionFactory sessionFactory;
	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
	}

	public List<BlogComment> getAllBlogComments(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost.id=:blogPostId");
		query.setInteger("blogPostId", blogPostId);
		//SQL - select * from blogcomment_s190038 where blogpost_id=?
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

}
