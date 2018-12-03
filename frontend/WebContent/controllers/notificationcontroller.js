
app.controller('NotificationCtrl',function($rootScope,NotificationService,$location,$scope,$routeParams){
	
	var notificationId=$routeParams.id
	function getAllNotifications(){
	NotificationService.getAllNotifications().then(
			function(response){
				//response.data ? Array of notifications not yet viewed
				$rootScope.notifications=response.data
				$rootScope.notificationCount=$rootScope.notifications.length
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
	}
	if(notificationId!=undefined){
	NotificationService.getNotification(notificationId).then(
			function(response){
				//response.data ? select * from notification where id=?
				//single notification object
				$scope.notification=response.data //notificationDetails.html
			},
			function(response){
				if(response.status==401)
					$location.path('/login')
			})
	
	NotificationService.updateNotification(notificationId).then(
			function(response){
				getAllNotifications()
				//call the function to execute the statement NotificationService.getAllNotifications()
			},
			function(response){
				if(response.status==401)
					$location.path('/login')
			})		
			
	}
	
	
	getAllNotifications()//call the function to execute the statement NotificationService.getAllNotifications()
})
