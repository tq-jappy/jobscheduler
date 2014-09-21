'use strict';

angular.module('ui.jobs', ['ngRoute', 'ngResource'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/jobs',          { templateUrl: 'jobs/index.html', controller: 'JobListCtrl' })
    .when('/jobs/new',      { templateUrl: 'jobs/new.html',   controller: 'JobNewCtrl' })
    .when('/jobs/:id/edit', { templateUrl: 'jobs/edit.html',  controller: 'JobEditCtrl' });    
}])

.factory("Job", function($resource) {
    var Job = $resource("/api/v1/jobs/:id", {}, {
        'update': {method: 'PUT', params: {id: '@id'}},
        'remove': {method: 'DELETE', params: {id: '@id'}}
    });
    return Job;
})

.controller('JobListCtrl', function($scope, $modal, $http, $resource, $routeParams, Job, AlertService) {    
    $scope.alerts = AlertService;
    
    $scope.jobs = Job.query(function() {
        console.log("get jobs");
        console.log($scope.jobs);
    });
 
    $scope.deleteJob = function(id) {
        console.log("delete - id : " + id);
        Job.delete({ id: id });
        $scope.jobs = Job.query();           
        AlertService.alerts = [ { type: 'success', msg: 'Deleted job with id: ' + id } ];
    };
})

.controller('JobNewCtrl', function($scope, $rootScope, $location, $modal, $http, Job, AlertService) {
    $scope.formData = {};
    
    $scope.createJob = function() {
        Job.save($scope.formData, function(job) {
          AlertService.alerts = [ {type: 'success', msg: 'New job created.'} ];
          $location.path("/");
        });
        $scope.formData = {};
    };    
})

.controller('JobEditCtrl', function($scope, $location, $modal, $http, $routeParams, Job, AlertService) {
    $scope.formData = {};
    
    Job.get({id: $routeParams.id}, function(job) {
        console.log("get job");
        console.log(job);
        $scope.job = job;
    });    

    $scope.updateJob = function() {
        console.log("updating.");
        console.log($scope.job);
        
        $scope.job.$update({}, function() {
           console.log('Success update');
           AlertService.alerts = [ {type: 'success', msg: 'Job updated.'} ];
           $location.path("/");
        });
    };      
});
