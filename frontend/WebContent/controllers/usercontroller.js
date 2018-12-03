/**
 * UserCtrl
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.registration=function(user){//user object from view 
		UserService.registration(user)//to service
		.then(function(response){
			$location.path('/login')
		},function(response){
			$scope.error=response.data
		})
	}
	
	$scope.login=function(user){
		UserService.login(user).then(
				function(response){
					//response.data is user object
					$rootScope.user=response.data
					$cookieStore.put('loggedInUser',response.data)
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
				})
	}
	
	
	
})