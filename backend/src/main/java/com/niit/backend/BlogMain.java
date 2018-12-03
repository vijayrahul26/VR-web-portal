package com.niit.backend;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.niit.config.DBConfig;
import com.niit.dao.BlogPostDAO;

import com.niit.model.BlogPost;


public class BlogMain 
{
public void blogOut()
{
	 AbstractApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
   	 BlogPostDAO blogPostDAO = (BlogPostDAO) context.getBean("blogpostDAO");
   	 
       
       BlogPost blogPost=new BlogPost();
       Scanner sc = new Scanner(System.in);
       
       System.out.println("Please select a category to do the manipulation");
		System.out.println("1.Add Blog");
		
		int choice = sc.nextInt();
		
		switch(choice){
		case 1:
			System.out.println("Please Enter the blog details to enter");
			
			System.out.println("Blog Title");
			blogPost.setBlogTitle(sc.next());
			
			System.out.println("Blog Content");
			blogPost.setBlogContent(sc.next());
			
			blogPostDAO.addBlogPost(blogPost);
		
			break;	
		 default: System.out.println("Please enter a valid input");
			
		}	
			context.close();
			sc.close();
}
}
