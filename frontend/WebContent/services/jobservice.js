/**
 * JobService
 */
app.factory('JobService',function($http){
var jobService={}
	
jobService.addJob=function(job){
	return $http.post("http://localhost:8060/middleware/job",job)
}

jobService.getAlljobs=function(){
	return $http.get("http://localhost:8060/middleware/job")
}

return jobService;
})
