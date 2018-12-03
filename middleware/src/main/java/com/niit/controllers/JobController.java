package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDAO;
import com.niit.dao.UserDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.Job;
import com.niit.model.User;

@RestController
@RequestMapping("job")
public class JobController 
{
	@Autowired
	private JobDAO jobDao;
	@Autowired
	private UserDAO userDao;
	
	@PostMapping
	public ResponseEntity<?> addJob(@RequestBody Job job,HttpSession session){
		//Authentication and Authorization
		String email=(String)session.getAttribute("email");//to check if the user is logged in
		System.out.println("SESSION ID IN addJob()" + session.getId());
		System.out.println("Session Attribute email in addJob()" + session.getAttribute("email"));
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		//User is authenticated, check for authorization
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz errorClazz=new ErrorClazz(6,"Access denied..");//
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		//User is authenticated and authorized [role of the logged in user is ADMIN]
		try{
			System.out.println(new Date());
		job.setPostedOn(new Date());	
		jobDao.addJob(job);
		}catch(Exception e){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unable to insert job details.."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAlljobs(HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}

	
}
