/**
	 * UserService
	 */
	app.factory('UserService',function($http){
		var userService={}
		
		userService.registration=function(user){//user from controller
			return $http.post("http://localhost:8060/middleware/register",user)
		}
		
		userService.login=function(user){
			return $http.put("http://localhost:8060/middleware/login",user)
		}
		
		
		userService.logout=function(){
			return $http.put("http://localhost:8060/middleware/logout")
		}
		
		return userService;
	})