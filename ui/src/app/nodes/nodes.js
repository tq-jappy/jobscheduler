'use strict';

angular.module('ui.nodes', ['ngRoute', 'ngResource'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/nodes',          { templateUrl: 'nodes/index.html', controller: 'NodeListCtrl' })
    .when('/nodes/new',      { templateUrl: 'nodes/new.html',   controller: 'NodeNewCtrl' })
    .when('/nodes/:id/edit', { templateUrl: 'nodes/edit.html',  controller: 'NodeEditCtrl' });    
}])

.factory("Node", function($resource) {
    var Node = $resource("/api/v1/nodes");
    return Node;
})

.controller('NodeListCtrl', function($scope, $modal, $http, $resource, $routeParams, Node) {
 
    $scope.nodes = Node.query(function() {
        console.log("get data");
        console.log($scope.nodes);
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
})

.controller('NodeNewCtrl', function($scope, $location, $modal, $http, Node) {
    $scope.formData = {};
    
    $scope.createNode = function() {
        Node.save($scope.formData, function(node) {
          // 作成に成功したら一覧に戻る
          $location.path("/");
        });
        $scope.formData = {};
    };  
})

.controller('NodeEditCtrl', function($scope, $modal, $http, $routeParams) {
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
});
