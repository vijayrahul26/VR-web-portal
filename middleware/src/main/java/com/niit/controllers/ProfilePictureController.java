package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
private ProfilePictureDAO profilePictureDao;
	@RequestMapping(value="/uploadprofilepic")
public ResponseEntity<?> saveOrUpdateProfilePicture(
		@RequestParam CommonsMultipartFile image,
		HttpSession session){
	String email=(String)session.getAttribute("email");
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	ProfilePicture profilePicture=new ProfilePicture();
	profilePicture.setEmail(email);
	profilePicture.setImage(image.getBytes());
	profilePictureDao.saveOrUpdateProfilePic(profilePicture);
	return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
}
	
	//logged in user -> john.s@niit.com
	//in blogpost , author of the blogpost "smith.s@niit.com"
	//url to get profilepic of john.s@niit.com <img src=".../getimage?email='john.s@niit.com"
	//url to get profilepic of smith.s@niit.com <img src=".../getimage?email='smith.s@niit.com"
	//url <img src
	@RequestMapping(value="/getimage",method=RequestMethod.GET)
	public @ResponseBody byte[]  getImage(@RequestParam String email,HttpSession session){
		String auth=(String)session.getAttribute("email");
		if(auth==null)
			return null;//src attribute will get null value, no image
		ProfilePicture profilePicture=profilePictureDao.getImage(email);
		if(profilePicture==null)
			return null;
		else
		return profilePicture.getImage();//this data will be return to src attribute of img tag
		
	}
}






