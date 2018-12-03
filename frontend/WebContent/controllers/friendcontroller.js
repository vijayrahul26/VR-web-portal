/**
 * 
 */
app.controller('FriendCtrl',function($scope,$location,FriendService){
	function getAllSuggestedUsers(){//Function definition
		FriendService.getAllSuggestedUsers().then(
				function(response){
					$scope.suggestedUsers=response.data//Array of User object
				},function(response){
					$scope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	$scope.sendFriendRequest=function(user){//user is the value for toId property in Friend entity
		FriendService.sendFriendRequest(user).then(function(response){
			//execute the query A - (B U C) again to get updated list
			alert('Friend request has been sent successfully..')
			getAllSuggestedUsers()
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	function getPendingRequests(){
	FriendService.getPendingRequests().then(function(response){
		$scope.pendingrequests=response.data
	},function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	$scope.acceptRequest=function(friendRequest){
		//friendRequest is an object of type Friend, it has friendId,fromId,toId,status='P'
		FriendService.acceptRequest(friendRequest).then(
				function(response){
					getPendingRequests()
				},function(response){
					$scope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	$scope.deleteRequest=function(friendRequest){
		FriendService.deleteRequest(friendRequest).then(
				function(response){
					getPendingRequests()
				},
				function(response){
					$scope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	//A-(B U C)and pendingrequest query will always gets executed
	getPendingRequests()
	getAllSuggestedUsers()//call the function
	FriendService.getAllFriends().then(
	function(response){
		$scope.friends=response.data
	},
	function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
	
})


