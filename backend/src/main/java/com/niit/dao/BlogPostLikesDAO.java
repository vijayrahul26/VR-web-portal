package com.niit.dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDAO 
{
	BlogPostLikes hasUserLikedBlogPost(int blogPostId,String email);

	BlogPost updateLikes(int blogPostId, String email);


}
