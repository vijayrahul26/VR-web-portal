package com.niit.backend;

import java.util.Scanner;

import com.niit.config.DBConfig;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
    {
    	DBConfig dbConfig=new DBConfig();
        dbConfig.sessionFactory();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your choice: 1.User");
        int choice=sc.nextInt();
        switch(choice)
        {
        case 1:
        {
        	UserMain userMain=new UserMain();
        userMain.userOut();
        break;
    }
        case 2:
        {
        	BlogMain blogMain=new BlogMain();
        	blogMain.blogOut();
        }
        default:
         	System.out.println("Please enter valid input");
        }
    }

}
