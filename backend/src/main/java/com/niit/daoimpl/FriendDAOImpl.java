package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;
import com.niit.model.User;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
		public List<User> getAllSuggestedUsers(String email) {
			String queryString="select * from customer where email in "
					+ "( select email from customer where email!=:email "
					+ " minus "
					+ "(select toId_email from friend where fromId_email=:email "
					+ " union "
					+ "select fromId_email from friend where toId_email=:email))";
			
			Session session=sessionFactory.getCurrentSession();
			SQLQuery sqlQuery=session.createSQLQuery(queryString);
			sqlQuery.addEntity(User.class);
			sqlQuery.setString("email", email);
			sqlQuery.setString("email", email);
			sqlQuery.setString("email", email);
			List<User> suggestedUsers=sqlQuery.list();
			return suggestedUsers;
		}
		public void addFriendRequest(Friend friend) {
			Session session=sessionFactory.getCurrentSession();
			session.save(friend);//insert into friend_s190038 values (?,fromId_email,toId_email,'P')
			
		}
		public List<Friend> getPendingRequests(String email) {
			Session session=sessionFactory.getCurrentSession();
			//property - friendId,fromId:User,toId:User,status:char
			//friend.toId.email=?
			String queryString="from Friend where toId.email=:email and status=:P";
			Query query=session.createQuery(queryString);
			query.setString("email", email);
			query.setCharacter("P", 'P');
			return query.list();
		}
		public void acceptRequest(Friend friendRequest) {
			Session session=sessionFactory.getCurrentSession();
			friendRequest.setStatus('A');
			session.update(friendRequest);//update friend_s190038 set status='A' where friendId=friendRequest.friendId
			//UPDATE
		}
		public void deleteRequest(Friend friendRequest) {
			Session session=sessionFactory.getCurrentSession();
			session.delete(friendRequest);//delete from friend_s190038 where friendId=friendRequest.friendId
		}
		public List<User> listOfFriends(String email) {
			Session session=sessionFactory.getCurrentSession();
			//f is alias
			Query query1=session.createQuery("select f.toId from Friend f where f.fromId.email=:email and f.status=:A");
			query1.setString("email", email);
			query1.setCharacter("A", 'A');
			List<User> list1=query1.list();//list of users who have accepted the request from logged in user
			
			Query query2=session.createQuery("select f.fromId from Friend f where f.toId.email=:email and f.status=:A");
			query2.setString("email", email);
			query2.setCharacter("A", 'A');
			List<User> list2=query2.list();//logged in user accepted the requests from other users
			
			list1.addAll(list2);//list1=list1 U  list2
			
			return list1;
		}

}
