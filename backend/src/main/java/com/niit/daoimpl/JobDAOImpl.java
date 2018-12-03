package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO
{
	@Autowired
	private SessionFactory sessionFactory;
		public void addJob(Job job) {
			Session session=sessionFactory.getCurrentSession();
			session.save(job);

		}
		public List<Job> getAllJobs() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");//HQL -> SQL 
			//HQL - from Job
			//SQL - select * from job
			List<Job> jobs=query.list();
			return jobs;

}
}