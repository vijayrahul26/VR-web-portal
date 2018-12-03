/**
 * JobCtrl
 */
app.controller('JobCtrl',function($scope,JobService,$location){
	$scope.isClicked=false;
	$scope.addJob=function(job){//job object from HTML,jobform.html -> jobs.html
		console.log(job)
		JobService.addJob(job).then(
				function(response){
					alert('Job details posted successfully..')
				    $location.path('/getalljobs')
				},
				function(response){
					$scope.error=response.data//$scope.error is an ErrorClazz object				
					if($scope.error.errorCode==5)
					$location.path('/login')
					
				})
	}
	
	//STATEMENT - CALL A FUNCTION IN JobService
	JobService.getAlljobs().then(function(response){
		//response.data?
		$scope.jobs=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
	$scope.showDetails=function(jobId){
		$scope.isClicked=!$scope.isClicked;
		$scope.jobId=jobId;
	}
    	
})
