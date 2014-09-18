'use strict';

angular.module('ui.nodes', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/nodes',     { templateUrl: 'nodes/nodes.html', controller: 'NodeCtrl' })
    .when('/nodes/new', { templateUrl: 'nodes/new.html', controller: 'NodeCtrl' });
}])

.controller('NodeCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.nodes = [];
    $scope.formData = {};
    
    $http.get('/api/v1/node').success(function(data) {
        $scope.nodes = data;
        console.log("get data");
    }).error(function(err) {
        console.log("error");
        console.log(err);
    });
    

    $scope.createNode = function() {
        console.log($scope.formData);
        $http.post('/api/v1/node', $scope.formData).success(function(data) {
            $scope.formData = {};           
            console.log('Success');
        }).error(function(data) {
            console.log('Error');
            console.log(data);
        });
    };
}]);