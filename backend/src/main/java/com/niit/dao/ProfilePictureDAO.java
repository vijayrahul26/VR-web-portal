package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDAO {
ProfilePicture  saveOrUpdateProfilePic(ProfilePicture profilePicture);
ProfilePicture  getImage(String email);
}
