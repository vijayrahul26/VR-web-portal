package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogPostDAO;
import com.niit.model.BlogPost;
@Repository("blogpostDAO")
@Transactional
public class BlogPostDAOImpl implements BlogPostDAO {
	@Autowired
private SessionFactory sessionFactory;
	public void addBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);

	}
	public List<BlogPost> blogsWaitingForApproval() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=0");
		return query.list();
	}
	public List<BlogPost> blogsApproved() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=1");
		return query.list();
	}
	public BlogPost getBlog(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogPostId);
		return blogPost;
	}
	public void updateBlogPost(BlogPost blogPost) {
	   Session session=sessionFactory.getCurrentSession();
	   session.update(blogPost);
		
	}
	public void deleteBlogPost(BlogPost blogPost) {
		 Session session=sessionFactory.getCurrentSession();
		 session.delete(blogPost);
		
	}

}

