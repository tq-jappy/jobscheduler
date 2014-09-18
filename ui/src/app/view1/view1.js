'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$http', function($scope, $http) {
	$scope.nodes = [];
	
	$http.get('/api/v1/node').success(function(data) {
		$scope.nodes = data;
		console.log("OK");
	}).error(function(err) {
		console.log("error");
		console.log(err);
	});	
}]);