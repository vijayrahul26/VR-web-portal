package com.niit.backend;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.niit.config.DBConfig;
import com.niit.dao.UserDAO;
import com.niit.model.User;

public class UserMain 
{
public void userOut()
{
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
  	 UserDAO userDAO = (UserDAO) context.getBean("userDAO");
  	 
      
      User user=new User();
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Please select a category to do the manipulation");
		System.out.println("1.Add User");
		
		int choice = sc.nextInt();
		
		switch(choice){
		case 1:
			System.out.println("Please Enter the user details to enter");
			
			System.out.println("Email");
			user.setEmail(sc.next());
			
			System.out.println("Password");
			user.setPassword(sc.next());
			
			System.out.println("First Name");
			user.setFirstname(sc.next());
			
			System.out.println("Last Name");
			user.setLastname(sc.next());
			
			System.out.println("Phone Number");
			user.setPhonenumber(sc.next());
			
			System.out.println("Role");
			user.setRole(sc.next());
			
			userDAO.registerUser(user);
		
			break;	

		case 2:
			System.out.println("Please enter the Email to view User Details");
			String email = sc.next();
			User user5 = userDAO.getUser(email);
			System.out.print("| First Name:"+user5.getFirstname()+"|");
			System.out.print("Last Name:"+user5.getLastname()+"|");
			System.out.print("Email Id:"+user5.getEmail()+"|");
			System.out.print("Phone Number:"+user5.getPhonenumber()+"|");
			System.out.print("Password:"+user5.getPassword()+"|");
			System.out.print("Role:"+user5.getRole()+"");
			
			break;
			
		case 3:

			List<User> userList4 = userDAO.findAllUsers();
			for(User user3 : userList4)
			{
				System.out.print("First Name:"+user3.getFirstname()+"|");
				System.out.print("Last Name:"+user3.getLastname()+"|");
				System.out.print("Phone Number:"+user3.getPhonenumber()+"|");
				System.out.print("Password:"+user3.getPassword()+"|");
				System.out.print("Role:"+user3.getRole()+"");
				System.out.print("Email:"+user3.getEmail()+"|");
				System.out.println("");

			}
			break;
			
		case 4:
			System.out.println("Please enter the Email to view User Details");
			String Email = sc.next();
			User user2 = userDAO.findUserByEmail(Email);
			System.out.print("First Name:"+user2.getFirstname()+"|");
			System.out.print("Last Name:"+user2.getLastname()+"|");
			System.out.print("Role:"+user2.getRole()+"|");
			System.out.print("Password:"+user2.getPassword()+"");
			System.out.print("PhoneNumber:"+user2.getPhonenumber()+"");
			
			break;

		 default: System.out.println("Please enter a valid input");
				
		}	
			context.close();
			sc.close();

}
}
