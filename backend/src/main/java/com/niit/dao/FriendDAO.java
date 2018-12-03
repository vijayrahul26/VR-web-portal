package com.niit.dao;


import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDAO {
List<User> getAllSuggestedUsers(String email);

void addFriendRequest(Friend friend);
List<Friend> getPendingRequests(String email);

void acceptRequest(Friend friendRequest);

void deleteRequest(Friend friendRequest);

List<User>  listOfFriends(String email);
}
