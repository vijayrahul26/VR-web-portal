package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.niit.dao.UserDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class UserController 
{
	@Autowired
	private UserDAO userDao;
	public UserController(){
		System.out.println("UserController bean is instantiated");
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public  ResponseEntity<?>  registration(@RequestBody User user){
		//check email is unique
		if(!userDao.isEmailUnique(user.getEmail())){
			ErrorClazz errorClazz=new ErrorClazz(2,"Email already exists.. choose some different email id");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try{
		userDao.registerUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){//Constraint violation
			ErrorClazz errorClazz=new ErrorClazz(1,"Unable to register user details "+ e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//i/p is user= {email:'adam.e@niit.com',password:'123'}
	//o/p validUser={email:'adam.e@niit.com',password:'123',firstname:'Adam',lastname:'E',.....}
	@RequestMapping(value="/login",method=RequestMethod.PUT)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		User validUser=userDao.login(user);
		if(validUser==null){
			ErrorClazz errorClazz=new ErrorClazz(3,"Invalid email id/password.. please enter valid credentials");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		else{
			validUser.setOnline(true);
			userDao.updateUser(validUser);
			System.out.println("Session Id Is " +session.getId());
			System.out.println("Session Creation Time is " + session.getCreationTime());
			session.setAttribute("email", validUser.getEmail());
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}

	@RequestMapping(value="/getallUsers",method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAllUsers(){
		
		return new ResponseEntity<List<User>>(userDao. findAllUsers(),HttpStatus.CREATED);
	}

	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){//user not logged in
			ErrorClazz errorClazz=new ErrorClazz(5,"please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		//update online status to false
		//remove session attribute "email"
		//invalidate the session
		//return success status code
		User user=userDao.getUser(email);
		user.setOnline(false);
		userDao.updateUser(user);//update online_status to false
		session.removeAttribute("email");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
