package com.niit.dao;

import java.util.List;

import com.niit.model.BlogPost;

public interface BlogPostDAO {
void  addBlogPost(BlogPost blogPost);
List<BlogPost> blogsWaitingForApproval();
List<BlogPost> blogsApproved();
BlogPost getBlog(int blogPostId);
void updateBlogPost(BlogPost blogPost);
void deleteBlogPost(BlogPost blogPost);
}
