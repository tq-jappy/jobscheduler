'use strict';

angular.module('ui.nodes', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/nodes',          { templateUrl: 'nodes/index.html', controller: 'NodeListCtrl' })
    .when('/nodes/new',      { templateUrl: 'nodes/new.html',   controller: 'NodeNewCtrl' })
    .when('/nodes/:id/edit', { templateUrl: 'nodes/edit.html',  controller: 'NodeEditCtrl' });    
}])

.controller('NodeListCtrl', ['$scope', '$modal', '$http', '$routeParams', function($scope, $modal, $http, $routeParams) {
    $scope.nodes = [];
    
    $http.get('/api/v1/nodes').success(function(data) {
        $scope.nodes = data;
        console.log("get data");
    }).error(function(err) {
        console.log("error");
        console.log(err);
    });
    
    $scope.deleteNode = function(id) {
        console.log("delete - id : " + id);
        
        $http.delete('/api/v1/nodes/' + id).success(function(data) {       
            console.log('Success delete');
            console.log(data);
        }).error(function(data) {
            console.log('Error');
            console.log(data);
        });
    };      
}])

.controller('NodeNewCtrl', ['$scope', '$modal', '$http', function($scope, $modal, $http) {
    $scope.formData = {};
    
    $scope.createNode = function() {
        console.log($scope.formData);
        $http.post('/api/v1/nodes', $scope.formData).success(function(data) {
            $scope.formData = {};           
            console.log('Success');
        }).error(function(data) {
            console.log('Error');
            console.log(data);
        });
    };  
}])

.controller('NodeEditCtrl', ['$scope', '$modal', '$http', '$routeParams', function($scope, $modal, $http, $routeParams) {
    $scope.formData = {};
    
    $http.get('/api/v1/nodes/' + $routeParams.id).success(function(data) {
        $scope.node = data;
        console.log("get data with id: " + $routeParams.id);
    }).error(function(err) {
        console.log("error");
        console.log(err);
    });

    $scope.updateNode = function() {
        console.log($scope.node);
        
        $http.put('/api/v1/nodes/' + $routeParams.id, $scope.node).success(function(data) {       
            console.log('Success update');
        }).error(function(data) {
            console.log('Error');
            console.log(data);
        });
    };      
}]);
