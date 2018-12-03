package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDAO 
{
	void addBlogComment(BlogComment blogComment);//insert into blogcomment table
	List<BlogComment> getAllBlogComments(int blogPostId);
	     //select * from blogcomments where  


}
