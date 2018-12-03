/**
 * BlogInDetailCtrl
 * /getBlogApprovalForm/:id
 * /getBlogApprovalForm/101
 */
app.controller('BlogInDetailCtrl',function($scope,BlogService,$location,$rootScope,$routeParams,$sce){
	var blogPostId=$routeParams.id //this gives the id of the blog post
	$scope.isRejected=false
	$scope.showComments=false
	if(blogPostId!=undefined){
	BlogService.getBlog(blogPostId).then(function(response){
		//response.data ? single blog post object -> select * from blogpost where id=?
		//get the details about that particular blogpost
		$scope.blogPost=response.data
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		$scope.error=response.data 
		if(response.status==401 && response.data.errorCode==5)
			$location.path('/login')
	})
	BlogService.hasUserLikedBlogPost(blogPostId).then(
	function(response){
		//response.data = '' or 1 blobpostlikes object
		if(response.data=='')
			$scope.isLiked=false
			else
				$scope.isLiked=true
	},
	function(response){
		$scope.error=response.data 
		if(response.status==401 && response.data.errorCode==5)
			$location.path('/login')
	})
	
	}
	$scope.approve=function(blogPost){
		BlogService.approve(blogPost).then(
		function(response){//change the url to list of blogs waiting for approval
			$location.path('/blogsWaitingForApproval/1')
		},function(response){
			// Not logged in or not authorized or exception 
			$scope.error=response.data //Not authorized or any exception in blogApprovalForm.html
			if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
				$location.path('/login')
		})
	}
	
	$scope.reject=function(blogPost,rejectionReason){
		if(rejectionReason==undefined)
			rejectionReason='Not Mentioned by Admin'
		BlogService.reject(blogPost,rejectionReason).then(
				function(response){
					$location.path('/blogsWaitingForApproval/1')
				},function(response){
					$scope.error=response.data //Not authorized or any exception in blogApprovalForm.html
					if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
						$location.path('/login')
				})
	}
	
	$scope.showTxtForRejectionReason=function(){
		$scope.isRejected=!$scope.isRejected
	}

	$scope.updateLikes=function(blogPostId){
		BlogService.updateLikes(blogPostId).then(
				function(response){
					$scope.blogPost=response.data //it is single blogpost object with updated likes
					$scope.isLiked=!$scope.isLiked
				},
				function(response){
					$scope.error=response.data //Not authorized or any exception in blogApprovalForm.html
					if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
						$location.path('/login')
				})
	}
	
	$scope.addBlogComment=function(blogPost,commentTxt){
		//CONSTRUCT BLOG COMMENT OBJECT 
		var blogComment={}//creating a blogcomment object 
		blogComment.blogPost=blogPost  //setting the values for two properties, blogpost and commenttxt
	    blogComment.commentTxt=commentTxt
		BlogService.addBlogComment(blogComment).then(function(response){
			//response.data ?
			$scope.blogComment=response.data
			$scope.commentTxt=""
		},function(response){
			$scope.error=response.data 
			if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
				$location.path('/login')
		})
	}
	 $scope.getAllBlogComments=function(){
	if(blogPostId!=undefined){
	$scope.showComments=!$scope.showComments
	BlogService.getAllBlogComments(blogPostId).then(
			function(response){
				//response.data -> List<BlogComment>
				//response.data -> select  * from blogcomment where blogpost_id=?
				$scope.blogComments=response.data
			},function(response){
				$scope.error=response.data 
				if(response.status==401 && response.data.errorCode==5)//Not loggedin, login.html
					$location.path('/login')
			})
	}
	}
})






