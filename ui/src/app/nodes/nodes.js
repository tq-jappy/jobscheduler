'use strict';

angular.module('ui.nodes', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/nodes',     { templateUrl: 'nodes/nodes.html', controller: 'NodeCtrl' });
//    .when('/nodes/new', { templateUrl: 'nodes/new.html', controller: 'NodeCtrl' });
}])

.controller('NodeCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.nodes = [];
    
    $http.get('/api/v1/node').success(function(data) {
        $scope.nodes = data;
        console.log("get data");
    }).error(function(err) {
        console.log("error");
        console.log(err);
    }); 
}]);